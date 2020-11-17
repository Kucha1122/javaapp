FROM java:8
COPY ./Program.java /
COPY ./mysql-connector-java-8.0.11.jar /   
WORKDIR /       
RUN javac Program.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.11.jar:.", "Program"]
