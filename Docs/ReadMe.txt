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
<1> Put log4j.jar into tomcat lib folder only (E:\Apache\tomcat-7.0.34\lib).
<2> Put ojdbc8.jar in libs folder inside your project. Right click on eclipse Project + Run Configuraions + Classpath + Select user Entries + Add Jars + Select the ojdbc8.jar + Apply +Ok.
(You can also put in Apache lib in case you deploy war outside eclipse, just like log4j.jar)
################################################################################