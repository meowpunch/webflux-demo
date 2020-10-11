curl -X GET "localhost:9200/omtm/recipe/_search" -H 'Content-Type: application/json' -d '
{
  "query": {
    "function_score": {
      "query": {
        "multi_match": {
          "query": "김치",
          "fields": ["description","title^0.5"]
        }
      },
      "random_score": {
        "field": "_seq_no"
      }
    }
  },
  "size": 2
}
'