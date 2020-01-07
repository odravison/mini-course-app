# Setup minicourses DB
docker run -d --name minicourses-db -e POSTGRES_USER=sogo -e POSTGRES_PASSWORD=sogochallenge123 -p 5432:5432 postgres:10 && \
docker exec -e PGPASSWORD=sogochallenge123 -d minicourses-db createdb --username=sogo --owner=sogo minicourses

# Setup api-server-test DB
docker exec -e PGPASSWORD=sogochallenge123 -d minicourses-db createdb --username=sogo --owner=sogo minicourses-test