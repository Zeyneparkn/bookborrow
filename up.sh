docker build -t bookborrow .
docker run --name bookborrow -p 8080:8080 -v m2-repo:/root/.m2 --rm -d bookborrow
