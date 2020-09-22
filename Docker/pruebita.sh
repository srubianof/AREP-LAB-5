#!/bin/bash
docker-compose down 
docker volume prune 
docker system prune -a 

docker-compose -f docker-composelocal.yml up -d --scale web=3
docker container ls
