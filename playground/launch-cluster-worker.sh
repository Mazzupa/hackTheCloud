#!/usr/bin/env bash
$SPARK_HOME/bin/spark-submit --master local\[4\] --jars ../elastic/elasticsearch-hadoop-6.5.0.jar cluster-worker.py