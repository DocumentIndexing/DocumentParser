#!/bin/bash
cat urls.txt | xargs -P 10 -I {} ./generateRequest.sh {}