/Applications/apache-tomcat-6.0.35/bin/shutdown.sh; 
rm -rf /Applications/apache-tomcat-6.0.35/webapps/temula/*;
rm -rf /Applications/apache-tomcat-6.0.35/webapps/temula.war
mvn clean package;
mv target/temula_person-0.0.4-SNAPSHOT.war target/temula.war;
cp target/temula.war /Applications/apache-tomcat-6.0.35/webapps/;
/Applications/apache-tomcat-6.0.35/bin/startup.sh;
