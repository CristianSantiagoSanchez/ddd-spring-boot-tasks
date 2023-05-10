package:
	mvn clean package -DskipTests

image:
	docker build -t cristianss/tasks-service:0.0.1-SNAPSHOT .

delete-image:
	docker rmi -f cristianss/tasks-service:0.0.1-SNAPSHOT

delete-container:
	docker rm tasks-task-service-1