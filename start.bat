@echo OFF
call mvn clean package
cd target
java -jar rps-1.0-SNAPSHOT.jar