#!/usr/bin/env bash

mvn clean install &&
mkdir target/deploy &&
cp target/short-url-1.0-SNAPSHOT.war target/deploy/ &&
docker image build -t mysimpleshorturl . &&
docker-compose up


