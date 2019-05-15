#!/bin/sh
mkdir -p docs
url=$1
content=$(curl -s ${url} |  base64 -w 0)
documentname=$(echo $url | sed 's/.*\/\([\.0-9a-zA-Z_\-]*\)/\1/')

echo "{" > docs/${documentname}.json;
echo "  \"url\" : \"${url}\"," >> docs/${documentname}.json;
echo "  \"filename\" : \"${documentname}\"," >> docs/${documentname}.json;
echo "  \"content\" : \"${content}\"" >> docs/${documentname}.json;
echo "}" >> docs/${documentname}.json;

curl -XPOST -H "Content-Type: application/json" localhost:8888/index/document -d @docs/${documentname}.json