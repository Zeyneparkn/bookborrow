FROM maven:3.8.4-openjdk-17
COPY src/ /app/src
COPY pom.xml /app

WORKDIR /app
RUN echo "execute in build"

CMD mvn clean install -DskipTests && java -jar target/bookborrow.jar

