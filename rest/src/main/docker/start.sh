#!/bin/sh

cd /home/gdd
java -Xmx2G -Djava.security.egd=file:/dev/./urandom -jar service.jar
