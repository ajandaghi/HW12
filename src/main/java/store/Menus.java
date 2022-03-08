package store;

import store.service.ProductService;

import java.util.Scanner;

public class Menus {
    Scanner scanner;
    String[] cmd;
    String input;

    public Menus() {
        this.scanner = new Scanner(System.in);
        input="";
    }

    public void mainMenu(){
        System.out.println("0.exit");
        System.out.println("1.sign up");
        System.out.println("2.sign in");
        input=scanner.nextLine();
    }

    public void registerMenu(){
        cmd=new String[3];
        System.out.println("enter fullName/nationalId/password");
        cmd=scanner.nextLine().split("/");
    }

    public void login(){
        System.out.println("-1.return store.main menu");
        System.out.println("0.exit");
        cmd=new String[2];
        System.out.println("1.enter user");
        cmd[0]=scanner.nextLine();
        System.out.println("2.enter pass");
        cmd[1]=scanner.nextLine();
    }
    
    public void customerMenu(){
        System.out.println("-1.log out");
        System.out.println("0.exit");
        System.out.println("1.edit my profile");
        System.out.println("2.add product to basket");
        System.out.println("3.remove product from basket");
        System.out.println("4.add budget");
        System.out.println("5.view my basket");
        System.out.println("6.finalize purchase");
        System.out.println("7.vie my bought product");
        input=scanner.nextLine();

    }

    public void adminMenu(){
        System.out.println("-1.log out");
        System.out.println("0.exit");
        System.out.println("1.edit my profile");
        System.out.println("2.add category");
        System.out.println("3.add new product");
        System.out.println("4.add to old product");
        System.out.println("5.remove product");
        System.out.println("6.view my sales");
        input=scanner.nextLine();

    }

    public void editProfile(){

    }

    public void addProductToBasket(){
        ProductService productService=new ProductService();
        productService.showAll();
        System.out.println("enter product id to add to basket");

    }


    }

