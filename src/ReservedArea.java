import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import enums.OperationToken;
import utils.Deserializer;
import utils.InputProcedures;
import utils.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.Scanner;

public class ReservedArea {
    private static  Socket socket=null;
    private static PrintWriter writer=null;
    private static BufferedReader reader=null;

    public static void init(Socket sock, PrintWriter write, BufferedReader read) {
        Scanner scanner = InputProcedures.scanner;
        socket=sock;
        writer=write;
        reader=read;

        try{
            int val=0;
            System.out.println("Welcome, "+new Date(System.currentTimeMillis()));
            System.out.println("1) Market order");
            System.out.println("2) Limit order");
            System.out.println("3) Stop order");
            System.out.println("4) Order history");
            System.out.println("5) Book");
            System.out.println("6) My orders");
            System.out.println("7) Logout");
            System.out.print("Enter choice: ");
            while (true) {
                if (scanner.hasNextInt()) {
                    val = scanner.nextInt();
                    scanner.nextLine();

                    switch (val) {
                        case 1 -> System.out.println("Market order selected");
                        case 2 -> System.out.println("Limit order selected");
                        case 3 -> System.out.println("Stop order selected");
                        case 4 -> System.out.println("Order history selected");
                        case 5 -> System.out.println("Book selected");
                        case 6 -> System.out.println("My orders selected");
                        case 7 -> {
                            Serializer msg = new Serializer(OperationToken.logout);
                            msg.setLogout();
                            writer.println(msg);
                            System.out.println(msg);
                            Deserializer resp = new Deserializer(reader.readLine());
                            System.out.println(resp);
                            return;
                        }
                        default -> System.out.println("Invalid option, please try again");
                    }
                }
            }
        }catch (SocketTimeoutException e){
            System.out.println("Connection closed, time out");
        } catch (UnknownJsonObject e){
            System.out.println("[Error] unknown response");
        }catch(ServerSocketClosed e){
            System.out.println("[Error] Connection lost");
        }catch (IOException e){
            System.out.println("[Error] Server connection failed");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
