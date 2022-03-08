package store;

import store.entity.enumeration.UserType;
import store.service.UserService;

public class Manager {
    UserService userService=new UserService();
    Menus menus=new Menus();

        public void main() {
            menus.mainMenu();
            switch (menus.input) {
                case "0":
                    System.exit(1000);
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                default :
                    System.out.println("wrong input");
                    menus.mainMenu();    


            }
        }

    private void login() {
menus.login();
if(userService.login(menus.cmd[0],menus.cmd[1])!=null){
    if(userService.login(menus.cmd[0],menus.cmd[1]).getUserType().equals(UserType.CUSTOMER)){
       customerMenu();
    } else
        adminMenu();

}
    }

    private void adminMenu() {
    }

    private void customerMenu() {
    }

    private void register() {
        menus.registerMenu();
        userService.add(menus.cmd[0],menus.cmd[1],menus.cmd[2], UserType.CUSTOMER);
        menus.login();

    }


}
