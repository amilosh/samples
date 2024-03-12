#### Build, deploy and run
docker compose up -d

docker-compose -f docker-compose-local.yml up -d

GET http://localhost:8080/person/send-message/username/message