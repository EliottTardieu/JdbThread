package fr.jdbc;

import dnl.utils.text.table.TextTable;


public class Main {

    public static void main(String[] args) {

        String[] columns = {"Hello", "Hi"};
        Object[][] data = {
                {0,1,2,3},
                {0,1*2,2*2,3*2}
            };
        TextTable ttable = new TextTable(columns, data);
        ttable.printTable();
        //App.getInstance();
    }

}
