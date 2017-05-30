#!/bin/bash
EXTRA_ARGS=--fast

working_dir=$(pwd)
che_version=$(cat pom.xml  | grep "<.*che.version\>" | grep -o  "[0-9].*[0.9].*[0-9]")

docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock \
	-v $working_dir/:/repo \
	-v $working_dir/data:/data \
	eclipse/che:$che_version \
	start --debug $EXTRA_ARGS
