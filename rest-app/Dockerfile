FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift
COPY api-services-jpa/target/uix-api-services-jpa-0.0.1-SNAPSHOT.jar /deployments/app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /deployments/app.jar" ]