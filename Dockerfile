FROM java:8
COPY ./program.java /etc
WORKDIR /etc
RUN javac program.java
CMD ["java", "-classpath", "mysql-connector-java-5.1.6.jar:.","Program"]
