#!/usr/bin/env python
'''
Resilient Distributed Dataset (RDD) reading
from a CSV file.
'''
from pyspark.sql import SparkSession

spark = SparkSession.builder \
	.appName('RDD Creation in PySpark') \
	.config('spark.some.config.key', 'value') \
	.getOrCreate()

df = spark.read.format('csv') \
	.options(header='true', inferschema='true') \
	.load('./comments.csv', header=True)

# Display
df.show(5)
df.printSchema()
