# Hibernate, a way to Database in Java.
### Run the .jar file after compiling

To run the application after compiling the source code, you will find in the target/out the jar file.   
You have to have your source code compiled with your mysql settings set up as said in the "how to get your jdbc Database to work" section for the jar to work.  
You should then open a terminal, and do a java -jar [filename].jar  
You should now see the application as it would be if compiled from your IDE.  
If you have any issues please contact eliott.tardieu@etu.univ-tours.fr or guillaume.cazin@etu.univ-tours.fr

### Run the source code in IntelliJ

To run the source code, you want to add a configuration.   
In this configuration, you will run the command "exec:java" via maven. You should also add a maven goal to "run before launch" with argument
"install" so it will compile your source code before running it.  
This whole project is using maven. If you need to edit maven configuration, please modify the pom.xml profect file
attached.

### Database Setup

Here we is the procedure to follow in order to install the database. All commands are meant to be run in bash.

<ol>
    <li>You must log in your mysql server using "mysql -u username -p".</li>
    <li>You will then create the database: "CREATE DATABASE s7_hibernate;".</li>
    <li>Then you log off from mysql and you go into the directory containing "s7_hibernate.sql".</li>
    <li>Once you are in the right directory, you will execute this command: "mysql -u root -p s7_hibernate < s7_hibernate.sql".</li>
    <li>After, to fill this database, you will execute this command: "mysql -u root -p s7_hibernate < s7_hibernate_data.sql".</li>
</ol>
You have now imported successfully the jdbc database, with already existing examples in it.

##### Now to get your jdbc Database to work:

<ol>
    <li>You will find in src/main/resources/config/ the config.properties.example file.</li>
    <li>You will copy paste it in the same directory and rename it to "config.properties".</li>
    <li>You will then enter your database (mysql) username and password.</li>
    <li>As it is in a conf.properties file which was gitignored, it will never be on git, so you can safely enter your 
        credentials, this is a simple trick to secure the application.</li>
</ol>
