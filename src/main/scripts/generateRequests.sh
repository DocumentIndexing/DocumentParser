#!/bin/bash
while read url; do  ./generateRequest.sh "$url"  ; done < urls.txt
