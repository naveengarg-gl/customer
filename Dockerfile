FROM openjdk:8
ADD customer-0.0.1-SNAPSHOT customer-0.0.1-SNAPSHOT
EXPOSE 8200
ENTRYPOINT ["java","-jar","customer-0.0.1-SNAPSHOT"]