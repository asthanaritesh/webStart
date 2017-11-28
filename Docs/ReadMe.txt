To Create the Oracle Database:
<1> start Database Configuration Assistant.
<2> Select Advanced Configuration (Do not select Typical). Next.
<3> Keep all default options on all further pages except:
<4> Global database name = WEBDB201
<5> Select "Specify Fast Recovery Area". Fast Recovery Area Size = 2G. It will give warning. Select yes to Contuinue.
<6> Select Create a New Listener:. Listener name="WEBDBLISTENER". Port = 2521.
<7> Use Automatic Shared Memory Management. Reduce the Total Memory to Max 4 GB, by dragging the bar. 
<8> Use the Same administrative password for all accounts. password =  Webdb201

Created Users in Db:
<1> admin : manager
<2> webapp : manager
############################################################################
mysql installation and setup
<1>Download mysql zip from https://dev.mysql.com/downloads/
<2> unzip at C:\mysql. create a data folder inside it (C:\mysql\data)
<3> Inside C:\mysql, create my.ini with following contents : 
[mysqld]
basedir="C:/mysql/"
datadir="C:/mysql/data/"
<4> Start the daemon(server): First Initialize it, Go to bin dir and type
>mysqld --defaults-file=C:\mysql\my.ini --initialize-insecure
then run
>mysqld --console
<5> Now another command window start mysql client
>mysql -u root
Then at mysql prompt set the root's password
mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY password;
<6> To coonect as mysql client:
>mysql -u root -p
or as webapp user:
>mysql -u webapp -p
<7> In case of any issues, Just delete the contents of data directory and restart with step <4>
================================================================================
Create User:
>CREATE USER 'webapp'@'localhost' IDENTIFIED BY 'manager';
>GRANT ALL PRIVILEGES ON * . * TO 'webapp'@'localhost';
Create Database:
>CREATE DATABASE webappdb;
To show current databse selected
>SELECT DATABASE();
To use a different databse:
>Use webappdb;
To directly login and select database:
>mysql -h localhost -u webapp -p webappdb
######################################################################################
<1> Put log4j.jar into tomcat lib folder only (E:\Apache\tomcat-7.0.34\lib).
<2> Put ojdbc8.jar/mysql-connector-java-5.1.44-bin.jar in libs folder inside your project. Right click on eclipse Project + Run Configuraions + Classpath + Select user Entries + Add Jars + Select the ojdbc8.jar + Apply +Ok.
(You can also put in Apache lib in case you deploy war outside eclipse, just like log4j.jar)
<3> Put hibernate-core-4.3.9.jar in libs folder and and add this as external jar in buildpath of project.
################################################################################
Start mysql DB (Go to C:\mysql\bin)
>>mysqld --console
To access the web page type:
http://localhost/WebStartApp
################################################################################


