package org.bank;

import java.sql.SQLException;
import java.text.ParseException;

public class Console {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        ConsoleMenus menu=new ConsoleMenus();
        menu.showMainMenu();
    }
}
