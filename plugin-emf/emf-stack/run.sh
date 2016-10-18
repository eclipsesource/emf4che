#!/bin/bash
docker run -ti --rm \
  -v /etc/localtime:/etc/localtime:ro \
eclipsesource/emfneon_jdk8 /bin/bash -l
