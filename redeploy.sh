/Applications/apache-tomcat-6.0.35/bin/shutdown.sh; 
rm -rf /Applications/apache-tomcat-6.0.35/webapps/temula*;
mvn clean package;
mv target/temula_person-0.0.3-SNAPSHOT.war target/temula.war;
cp target/temula.war /Applications/apache-tomcat-6.0.35/webapps/;
/Applications/apache-tomcat-6.0.35/bin/startup.sh;
