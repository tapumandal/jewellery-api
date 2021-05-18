FROM openjdk:8
ADD target/super-haat-api.jar super-haat-api.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "super-haat-api.jar"]

## MySql pull then run with credentials
# docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tapudb -e MYSQL_USER=user-t4pu -e MYSQL_PASSWORD=password -d mysql:5.6
#
# docker run --net=my-network --name mysql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=ims-db -e MYSQL_USER=ims-user -e MYSQL_PASSWORD
#=password -d mysql:5.6

## phpmyadmin pull then run with mysql server linked    
# docker run --name myphpmyadmin -d --link mysql-server:db -p 8001:80 phpmyadmin/phpmyadmin

## Spring app Build
# docker build . -t super-haat-api
## App Run and link with mysql server
# docker run -p 8080:8080 --name super-haat-api  --link mysql-server:mysql -d super-haat-api

#RUN AND BUILD TOGETHER WITHN SPECIFIC NETWORKS
#docker build -t super-haat-api . && docker run --net=my-network -p 8080:8080 --name super-haat-api  --link mysql-server:mysql -d
#super-haat-api

#docker build -t super-haat-api . && docker run -p 80:8081 --name super-haat-api --link mysql-server:mysql tapumadnal/super-haat-api