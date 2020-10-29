FROM java:8
COPY ./program.java /etc
WORKDIR /etc
RUN javac program.java
CMD java Program