package utils;

import enums.OperationToken;

import java.util.Scanner;

public class InputProcedures {

    public static String login(int port){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        Serializer values = new Serializer(OperationToken.login);
        values.setLogin(username, password,port);
        scanner.close();
        return values.toString();
    }

    public static String register(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        Serializer values = new Serializer(OperationToken.register);
        values.setRegister(username, password);
        scanner.close();
        return values.toString();
    }

    public static String updateCredentials(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Old password: ");
        String old_password = scanner.nextLine();
        System.out.println("New password: ");
        String new_password = scanner.nextLine();
        Serializer values = new Serializer(OperationToken.updateCredentials);
        values.updateCredentials(username, old_password,new_password);
        scanner.close();
        return values.toString();
    }

}
