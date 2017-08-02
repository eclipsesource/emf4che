#!/bin/bash
EXTRA_ARGS=--fast

working_dir=$(pwd)
che_version=$(cat pom.xml  | grep "<.*che.version\>" | grep -o  "[0-9].*[0.9].*[0-9]")
DATE=$(date +%Y-%m-%d-%H-%M-%S)

docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock \
       -v $working_dir/assembly/assembly-main/target/eclipse-che-1.0.0-SNAPSHOT/eclipse-che-1.0.0-SNAPSHOT:/assembly \
       -v $working_dir/che-data/$DATE/:/data \
       eclipse/che-cli:$che_version start --debug $EXTRA_ARGS
