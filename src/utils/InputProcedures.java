package utils;

import enums.MarketType;
import enums.OperationToken;

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
                return ser.toString();
            }catch (InputMismatchException ignored){
                System.out.println("Input not valid");
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
                return ser.toString();
            }catch (InputMismatchException ignored){
                System.out.println("Input not valid");
                scanner.nextLine();
            }
        }

    }

}
