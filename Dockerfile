FROM tomcat:8-jre8
RUN rm -rf $CATALINA_HOME/webapps/*
ADD ./target/*.war $CATALINA_HOME/webapps/
ADD ./target/context.xml $CATALINA_HOME/webapps/manager/META-INF/context.xml
ADD ./target/context.xml $CATALINA_HOME/webapps/host-manager/META-INF/context.xml
EXPOSE 8080
CMD catalina.sh run;
