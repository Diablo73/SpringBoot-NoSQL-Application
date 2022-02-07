git pull origin master
echo
(java -jar target/SpringBoot-NoSQL-Application-0.0.1-SNAPSHOT.jar & mvn clean package)
clear
jps
kill -9 $(ps -ef | grep target/SpringBoot-NoSQL-Application-0.0.1-SNAPSHOT.jar | awk '/java/ {print $2}')
java -jar target/SpringBoot-NoSQL-Application-0.0.1-SNAPSHOT.jar
