package utils;

import enums.OperationToken;

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

}
