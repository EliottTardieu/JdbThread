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
    <li>You must create a "persistence.xml and edit its properties to make them fit your needs.</li>
    <li>You may then log in your mysql server using "mysql -u username -p".</li>
    <li>You will then create the database: "CREATE DATABASE s7_hibernate;".</li>
    <li>OPTIONAL: Then you log off from mysql and you go into the directory containing "s7_hibernate.sql".</li>
    <li>OPTIONAL: Once you are in the right directory, you will execute this command: "mysql -u root -p s7_hibernate < s7_hibernate.sql".</li>
    <li>OPTIONAL: After, to fill this database, you will execute this command: "mysql -u root -p s7_hibernate < s7_hibernate_data.sql".</li>
</ol>
You have now imported successfully the database, with already existing examples in it.

You can also decide not to create the tables before running the program, you can then adjust the "persistence.xml" file to make it fit your needs.

##### Now to get your Database to work:

Do not forget to edit the persistence.xml by writing the correct username and password, and adding the correct link name for the EntityManagerFactory.
