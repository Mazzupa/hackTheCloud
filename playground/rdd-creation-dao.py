#!/usr/bin/env python
'''
Resilient Distributed Dataset (RDD) reading
from a DAO source. This example specifically
targets an Elastic DAO backend.
'''
from pyspark.sql import SparkSession

spark = SparkSession.builder \
	.appName('RDD Creation in PySpark') \
	.config('es.index.auto.create', 'true') \
	.getOrCreate()

df = spark.read.format('csv') \
	.options(header='true', inferschema='true') \
	.load('./comments.csv', header=True)

# Display
df.show(5)
df.printSchema()
