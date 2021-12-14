package fr.jdbc;

public class App {

    private static App instance;

    private App(){}

    public static App getInstance(){
        if(instance == null) return new App();
        return instance;
    }

}
