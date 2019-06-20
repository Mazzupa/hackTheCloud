#!/usr/bin/env python
'''
TF-IDF + K-Means Spark model trainer
'''
from functools import reduce
from pyspark.ml.feature import StopWordsRemover
from pyspark.mllib.feature import HashingTF, IDF
from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import json

# Scikit-Learn
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cluster import KMeans
from sklearn.metrics import adjusted_rand_score

# TextBlob
from textblob import TextBlob


## Globals
ES_INDEX_NAME = 'issues'
ES_TYPE_NAME  = 'issue'
ES_ID_FIELD   = '_id'

TF_FEATURE_SIZE = 1000000
KMEANS_N_CLUSTERS = 2
KMEANS_N_INIT = 1
KMEANS_MAX_ITER = 100

def main_entry():
  es_cfg_file = open('../conf/es-cluster-config.json', 'r')
  es_host_cfg = json.loads(reduce((lambda x, y: x + y), es_cfg_file.readlines()))

  spark = SparkSession.builder \
    .appName('SparkClusterWorker') \
    .config('es.index.auto.create', 'true') \
    .getOrCreate()
  sc = spark.sparkContext

  rdd_read_conf = {
    'es.nodes': es_host_cfg['host'],
    'es.nodes.wan.only': 'true',
    'es.net.http.auth.user': es_host_cfg['user'],
    'es.net.http.auth.pass': es_host_cfg['pass'],
    'es.resource': "%s/%s" % (ES_INDEX_NAME, ES_TYPE_NAME) 
  }

  es_rdd = sc.newAPIHadoopRDD(
    inputFormatClass="org.elasticsearch.hadoop.mr.EsInputFormat",
    keyClass="org.apache.hadoop.io.NullWritable", 
    valueClass="org.elasticsearch.hadoop.mr.LinkedMapWritable", 
    conf=rdd_read_conf)
  
  df = es_rdd.map(lambda row: row[1]).toDF()
  comments = df.select(df.comment).collect()
  comments = list(map(lambda c: c.comment, comments))

  #print ("****************************COMMENTI****************************")
  #print (comments)
  #print ("****************************************************************")
  allComment = ". ".join(comments)
  blob = TextBlob(allComment)
  blobCorrect = blob.correct()
  commentSentiment = []
  for i in range(len(blobCorrect.sentences)):
    commentSentiment.append(tuple((blob.sentences[i], blobCorrect.sentences[i].polarity)))
  for i in range(len(commentSentiment)):
    print(str(commentSentiment[i][0])+" "+str(commentSentiment[i][1])+"\n")
  
  #RISULTATO SENTIMENT ANALYSIS

  # print(comments)

  ## Vectorize
  tfidf_vec = TfidfVectorizer(stop_words="english")
  x = tfidf_vec.fit_transform(comments)

  ## K-Means
  model = KMeans(n_clusters=KMEANS_N_CLUSTERS, max_iter=KMEANS_MAX_ITER, n_init=KMEANS_N_INIT)
  model.fit(x)

  ## Print top 10 keywords for each cluster
  centroids = model.cluster_centers_.argsort()[:, ::-1]
  keywords = tfidf_vec.get_feature_names()

  for i in range(KMEANS_N_CLUSTERS):
    print("[+] Cluster %d:" % i)

    for j in centroids[i, :10]:
      print("%d. %s" % (j, keywords[j]))
  
  # df = df.withColumn('Comment_Split', split(df.comment, r'[\s,:;.!?]')) \
  #   .drop(df.comment) \
  #   .select(col('Comment_Split').alias('Comment'))
  
  # Remove stopwords
  # swr = StopWordsRemover(inputCol="Comment", outputCol="FilteredComment")
  # df = swr.transform(df)

  # df.show()
  # df1 = df.select(df.Comment).collect()
  # df1.show()
  # df = df.withColumn('DocId', monotonically_increasing_id())
  # df = df.select("FilteredComment")

  # TF-IDF
  # hashingTF = HashingTF(TF_FEATURE_SIZE)
  # tf = hashingTF.transform(rdd)
  # tf.cache()
  # idf = IDF(minDocFreq=1).fit(tf)
  # tfidf = idf.transform(idf)
  # print(tfidf.collect())

if __name__ == '__main__':
  main_entry()