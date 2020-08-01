#!/bin/bash

echo "will be executed on next step -> docker run --name jt-mysql57 -p 33006:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:5.7"
docker run --name jt-mysql57 -p 33006:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql/mysql-server:5.7
echo "will be executed on next step -> docker cp setup_db.sh jt-mysql:/home/setup_db.sh"
docker cp setup_db.sql jt-mysql57:/home/setup_db.sql
echo "will be executed on next step -> docker exec -it jt-mysql /bin/bash"
docker exec -it jt-mysql57 /bin/bash




