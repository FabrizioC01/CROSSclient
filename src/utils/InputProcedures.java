package utils;

import enums.MarketType;
import enums.OperationToken;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputProcedures {
    public static Scanner scanner = new Scanner(System.in);

    public static String login(int port){
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        Serializer values = new Serializer(OperationToken.login);
        values.setLogin(username, password,port);
        return values.toString();
    }

    public static String register(){
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        Serializer values = new Serializer(OperationToken.register);
        values.setRegister(username, password);
        return values.toString();
    }

    public static String updateCredentials(){
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Old password: ");
        String old_password = scanner.nextLine();
        System.out.println("New password: ");
        String new_password = scanner.nextLine();
        Serializer values = new Serializer(OperationToken.updateCredentials);
        values.updateCredentials(username, old_password,new_password);
        return values.toString();
    }

    public static String insertLimitStopOrder(boolean isStop){
        while (true){
            try {
                System.out.println("Operation type (0:ask | 1:bid): ");
                int v = scanner.nextInt();
                if(v<0 || v>1) {
                    System.out.println("Invalid operation...");
                    scanner.nextLine();
                    continue;
                }
                System.out.println("Insert price: ");
                int price = scanner.nextInt();
                if(price<=0) {
                    System.out.println("Invalid price...");
                    scanner.nextLine();
                    continue;
                }
                System.out.println("Insert size: ");
                int size = scanner.nextInt();
                if(size<=0) {
                    System.out.println("Invalid size...");
                    scanner.nextLine();
                    continue;
                }
                Serializer ser = new Serializer((isStop)?OperationToken.insertStopOrder:OperationToken.insertLimitOrder);
                ser.setLimitStop((v==0)?MarketType.ask:MarketType.bid,size,price);
                scanner.nextLine();
                return ser.toString();
            }catch (InputMismatchException ignored){
                System.out.println("Input not valid");
                scanner.nextLine();
            }
        }

    }
    public static String cancelOrder(){
        while (true){
            try {
                System.out.println("Order id: ");
                int v = scanner.nextInt();
                if(v<0) {
                    System.out.println("Invalid value...");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();
                Serializer ser = new Serializer(OperationToken.cancelOrder);
                ser.setOrderCancel(v);
                return ser.toString();
            }catch (InputMismatchException ignored){
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }


    public static String insertMarketOrder(){
        while (true){
            try {
                System.out.println("Operation type (0:ask | 1:bid): ");
                int v = scanner.nextInt();
                if(v<0 || v>1) {
                    System.out.println("Invalid operation...");
                    scanner.nextLine();
                    continue;
                }
                System.out.println("Insert size: ");
                int size = scanner.nextInt();
                if(size<=0) {
                    System.out.println("Invalid size...");
                    scanner.nextLine();
                    continue;
                }
                Serializer ser = new Serializer(OperationToken.insertMarketOrder);
                ser.setMarket((v==0)?MarketType.ask:MarketType.bid,size);
                scanner.nextLine();
                return ser.toString();
            }catch (InputMismatchException ignored){
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }

    }
    public static String getHistory(){
        while (true){
            try {
                System.out.println("Month (1-12): ");
                int month = scanner.nextInt();
                if(month<1 || month>12) {
                    System.out.println("Invalid month...");
                    scanner.nextLine();
                    continue;
                }
                System.out.println("Year (ex. 2020): ");
                int year = scanner.nextInt();
                if(year<=0 || year> Year.now().getValue()) {
                    System.out.println("Invalid year...");
                    scanner.nextLine();
                    continue;
                }
                Serializer ser = new Serializer(OperationToken.getPriceHistory);
                String val = Integer.toString(month)+Integer.toString(year);
                if(month<10) val = "0"+val;
                ser.setHistory(val);
                scanner.nextLine();
                return ser.toString();
            }catch (InputMismatchException ignored){
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

}
