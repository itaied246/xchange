FROM openjdk:8-jre

WORKDIR /opt/webapp

ADD target/uberjar/xchange-app-standalone.jar .

CMD java -jar xchange-app-standalone.jar

