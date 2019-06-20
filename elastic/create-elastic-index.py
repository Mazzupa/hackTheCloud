#!/usr/bin/env python
'''
Ingest raw issue data into an Elastic index.
This file is based off @sloanahrens's `build_index.py`
'''
from functools import reduce
import elasticsearch as els
import csv
import json

## Globals
ES_INDEX_NAME = 'issues'
ES_TYPE_NAME  = 'issue'

if __name__ == '__main__':
    ## --- ES Config ---
    es_cfg_file = open('../conf/es-cluster-config.json', 'r')
    es_host_cfg = json.loads(reduce((lambda x, y: x + y), es_cfg_file.readlines()))

    ## --- Data Parse ---
    csv_file = open('issues-index.csv', 'r')
    csv_file_obj = csv.reader(csv_file)
    
    csv_hdr = next(csv_file_obj)
    csv_hdr = [hh.lower() for hh in csv_hdr]

    data = [] 

    for row in csv_file_obj:
        data_dict = {}

        for f in range(len(row)):
            data_dict[csv_hdr[f]] = row[f]

        op_dict = {
            'index': {
                '_index': ES_INDEX_NAME, 
                '_type':  ES_TYPE_NAME, 
                # '_id': data_dict[ES_ID_FIELD]
            }
        }

        data.append(op_dict)
        data.append(data_dict)

    ## --- Data Ingestion ---
    hosts = ["%s:%s@%s" % (es_host_cfg['user'], es_host_cfg['pass'], es_host_cfg['host'])]
    es = els.Elasticsearch(hosts = hosts)

    if es.indices.exists(ES_INDEX_NAME):
        print("[+] Deleting '%s' index..." % (ES_INDEX_NAME))
        res = es.indices.delete(index = ES_INDEX_NAME)
        print("[+] `Delete` returned with: '%s'" % (res))

    request_body = {
        'settings' : {
            'number_of_shards': 1,
            'number_of_replicas': 0
        }
    }

    print("[+] Creating '%s' index..." % (ES_INDEX_NAME))
    res = es.indices.create(index = ES_INDEX_NAME, body = request_body)
    print("[+] `Create` returned with: '%s'" % (res))

    res = es.bulk(index = ES_INDEX_NAME, body = data, refresh = True)

    # Check
    res = es.search(index = ES_INDEX_NAME, body = {'query': {'match_all': {}}})
    print("[+] `Search` returned with: '%s'" % (res))

    for hit in res['hits']['hits']:
        print(hit["_source"])