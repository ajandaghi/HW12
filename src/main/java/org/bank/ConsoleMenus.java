package org.bank;

import org.bank.entity.*;
import org.bank.repository.TransRepository;
import org.bank.services.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleMenus {
    Scanner scanner=new Scanner(System.in);
    private StaffsActions staffsActions;
    private AccountService accountService;
    private CardService cardService;
    private CustomerService customerService;
    private TransactionService transactionService;
    private BranchService branchService;

    public ConsoleMenus() throws SQLException, ClassNotFoundException {
        staffsActions =new StaffsActions();
        accountService=new AccountService();
        cardService= new CardService();
        customerService=new CustomerService();
        transactionService=new TransactionService();
        branchService=new BranchService();
    }

    public void showMainMenu() throws SQLException, ParseException, ClassNotFoundException {
        System.out.println("enter what you want to do:");
        System.out.println("-1.exit");
        System.out.println("1. log-in");
        String input=scanner.nextLine();

        switch (input){
            case "-1":
                return;
            case "1":
                showLoginMenu();
                break;
            default:
                System.out.println("wrong input");
                showMainMenu();

        }
    }

    private void showLoginMenu() throws SQLException, ParseException, ClassNotFoundException {
        System.out.println("select:");
        System.out.println("-1.return main menu");
        System.out.println("1.admin");
        System.out.println("2.staffs(Employee and Boss)");
        System.out.println("3.Customer");
        String input=scanner.nextLine();
       login(input);
    }

    public void login(String input) throws SQLException, ParseException, ClassNotFoundException {
        String user="";
        String pass="";

        switch (input){
            case "-1":
                showMainMenu();
                break;
            case "1":
                System.out.println("enter user:");
                user=scanner.nextLine();
                System.out.println("enter pass:");
                pass=scanner.nextLine();

                if(staffsActions.adminLogin(user,pass)){
                    showAdminMenu();
                } else {
                    System.out.println("wrong credential");
                    login(input);                }
                break;
            case "2":
                System.out.println("enter user:");
                user=scanner.nextLine();
                System.out.println("enter pass:");
                pass=scanner.nextLine();

                if(staffsActions.login(user,pass).equals(StaffType.Boss)){
                    showBossMenu(user);
                } else if(staffsActions.login(user,pass).equals(StaffType.Employee)) {
                    System.out.println(staffsActions.login(user,pass));
                    showAccountMenu(user);
                }else{

                    System.out.println("wrong credential");
                    login(input);
                }


                break;

            case "3":
                System.out.println("enter user:");
                user=scanner.nextLine();
                System.out.println("enter pass:");
                pass=scanner.nextLine();

                if(customerService.CustomerLogin(user,pass)){
                    showCardMenu(user);
                }else {
                    login(input);
                }



        }
    }

    private void showBossMenu(String user) throws SQLException, ParseException, ClassNotFoundException {
        System.out.println("select:");
        System.out.println("-1.logout");
        System.out.println("1.add staffs");
        System.out.println("2.Account Menu");

        String input=scanner.nextLine();

        switch (input) {
            case "-1":
                showLoginMenu();
                break;
            case "1":
                System.out.println("enter: user/pass/fullName/branchNo");
               String[] cmd=scanner.nextLine().split("/");
                staffsActions.addEmployee(cmd[0],cmd[1],cmd[2],cmd[3]);
                showAdminMenu();
                break;

            case "2":
        showAccountMenu(user);
        break;
        }


    }

    private void showAdminMenu() throws SQLException, ParseException, ClassNotFoundException {
        System.out.println("select:");
        System.out.println("-1.log out");
        System.out.println("1.add branch");
        System.out.println("2.add staffs");
        System.out.println("3.add branch Boss");

        String input = scanner.nextLine();
        String[] cmd;
        switch (input) {
            case "-1":
                showLoginMenu();
                break;
            case "1":
                System.out.println("enter: branchNo/branchName/address");
                cmd=scanner.nextLine().split("/");
                staffsActions.addBranch(cmd[0],cmd[1],cmd[2]);
                showAdminMenu();
                break;

            case "2":
                System.out.println("enter: user/pass/fullName/branchNo");
                cmd=scanner.nextLine().split("/");
                staffsActions.addEmployee(cmd[0],cmd[1],cmd[2],cmd[3]);
                showAdminMenu();
                break;

            case "3":
                System.out.println("enter: user/pass/fullName/branchNo");
                cmd=scanner.nextLine().split("/");
                staffsActions.addBranchBoss(cmd[0],cmd[1],cmd[2],cmd[3]);
                showAdminMenu();
                break;


            default:
                System.out.println("wrong input");
        }
    }

    public void showAccountMenu(String user) throws SQLException, ParseException, ClassNotFoundException {
        System.out.println("select:");
        System.out.println("-1.return");
        System.out.println("1.show Accounts");
        System.out.println("2.find Account By CustomerId");
        System.out.println("3.add Account");
        System.out.println("4.delete a Account");
        System.out.println("5.activate a customer");
        System.out.println("6.add card to account");

        String input = scanner.nextLine();
        switch (input) {
            case "-1":
                showAdminMenu();
            case "1":
                accountService.showAllAccount(user);
                showAccountMenu(user);
            break;
            case "2":
                System.out.println("enter customerId");
                int custId = Integer.parseInt(scanner.nextLine());
                accountService.selectByCustomerId(user,custId);
                showAccountMenu(user);
                break;

            case "3":
                System.out.println("accountType/customerUser");
                String[] cmd = scanner.nextLine().split("/");

                accountService.addAccount(branchService.getBranchNo(staffsActions.getBranchIdByUser(user)), AccountType.valueOf(cmd[0]), cmd[1]);
                showAccountMenu(user);
                break;

            case "4":
                System.out.println("enter AccountNo:");
                String accountNo = scanner.nextLine();
                accountService.deleteAccount(accountNo);
                showAccountMenu(user);

                break;
            case "5":
                System.out.println("enter customerId:");
                int customerId = Integer.parseInt(scanner.nextLine());
                Customers customer=customerService.selectCustomerByUser(customerService.getCustomerUserById(customerId));
                customer.setEnable(true);
                customerService.updateByUser(customerService.getCustomerUserById(customerId),customer);
                System.out.println("customer account enabled.");
                showAccountMenu(user);

                break;
            case "6":
                System.out.println("enter AccountNo:");
                accountNo = scanner.nextLine();
                Date date= new java.sql.Date(System.currentTimeMillis());
                Random random=new Random();
                cardService.addCardToAccount(accountNo, (date.getYear()+4)+""+date.getMonth(),String.valueOf(random.nextInt(8999)+1000));
                showAccountMenu(user);
                break;
            default:
                System.out.println("wrong input");
                showAccountMenu(user);


        }
    }



        public void showCardMenu(String user) throws SQLException, ClassNotFoundException, ParseException {
            System.out.println("select:");
            System.out.println("-1.log out");
            System.out.println("1.show my cards");
            System.out.println("2.card to card");
            System.out.println("3.change password");
            System.out.println("4.change pass2");

            String input=scanner.nextLine();

            switch(input){
                case "-1":
                    showLoginMenu();
                    break;
                case "1":
                  List<Account> accounts= accountService.selectByCustomerId(user,(customerService.getLastCustomerId(user)));
                  for(int i=0;i<accounts.size();i++){
                      if(cardService.selectCardByAccountId(accounts.get(i).getId())!=null)
                      System.out.println(cardService.selectCardByAccountId(accounts.get(i).getId()).toString());
                  }
                    showCardMenu(user);
                  break;
                case "2":
                    System.out.println("enter your card no: ");
                    String fromCardNo = scanner.nextLine();
                    System.out.println("enter your destination card no: ");
                    String toCardNo = scanner.nextLine();
                    System.out.println("enter amount: ");
                    Long amount=scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("enter pass2: ");
                    String pass2=scanner.nextLine();
                    System.out.println("enter cvv2: ");
                    String cvv2=scanner.nextLine();


                        while(cardService.cardToCard(user.trim(),fromCardNo.trim(),toCardNo.trim(),amount,cvv2.trim(),pass2.trim())!=1) {
                        System.out.println("enter your card no: ");
                        fromCardNo = scanner.nextLine();
                        System.out.println("enter your destination card no: ");
                        toCardNo = scanner.nextLine();
                        System.out.println("enter amount: ");
                             amount=scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("enter pass2: ");
                        pass2=scanner.nextLine();
                        System.out.println("enter cvv2: ");
                        cvv2=scanner.nextLine();

                    } ;
                    showCardMenu(user);
                    break;

                case "3":
                    System.out.println("enter new password");
                    String password=scanner.nextLine();
                    Customers customer=customerService.selectCustomerByUser(user);
                    customer.setPass(password);
                    customerService.updateByUser(user,customer);
                    break;
                case "4":
                    System.out.println("enter cardId:");
                    int cardId=Integer.parseInt(scanner.nextLine());
                    System.out.println("enter new pass2");
                    String password2=scanner.nextLine();
                    Cards card =cardService.selectCardByCardId(cardId);
                    card.setPass2(password2);
                    break;
                default:
                    System.out.println("wrong input");
            }

        }

        public void transactionMenu(String user) throws SQLException, ClassNotFoundException {
            System.out.println("select:");
            System.out.println("1. transaction after a date YYYY-MM-DD");
            System.out.println("2. transaction after a date and by accountId");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("enter date as YYYY-MM-DD");
                    String date = scanner.nextLine();
                    transactionService.seerchByDate(user,date);
                    break;

                case "2":
                    System.out.println("enter date as YYYY-MM-DD");
                     date = scanner.nextLine();
                     int accountId=Integer.parseInt(scanner.nextLine());
                    transactionService.seerchByDateAndAccountId(user,date,accountId);
                    break;


            }
        }
}
