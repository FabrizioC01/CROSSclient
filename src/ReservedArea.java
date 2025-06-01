import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import Models.Trade;
import Services.NotificationService;
import enums.OperationToken;
import utils.Deserializer;
import utils.InputProcedures;
import utils.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
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
            int sel=1;
            while (sel>=1 && sel<=7) {
                printMenu();
                String line = scanner.nextLine();
                try{
                    sel = Integer.parseInt(line);
                    switch(sel) {
                        case 1 -> {
                            String req = InputProcedures.insertMarketOrder();
                            writer.println(req);
                            String resp = reader.readLine();
                            Deserializer des = Deserializer.fromOrderResponse(resp);
                            if(des.getCode()==-1) System.out.println("[Server] Error order not completed ("+des.getCode()+")");
                            else System.out.println("[Server] Market order completed with id: "+des.getCode());
                        }
                        case 2 -> {
                            String resp = InputProcedures.insertLimitStopOrder(false);
                            writer.println(resp);
                            String l = reader.readLine();
                            Deserializer des = Deserializer.fromOrderResponse(l);
                            if(des.getCode()!=-1) System.out.println("[Server] Limit Order created with id: "+des.getCode());
                            else System.out.println("[Server] Error limit order not created ("+des.getCode()+")");
                        }
                        case 3 -> {
                            String resp = InputProcedures.insertLimitStopOrder(true);
                            writer.println(resp);
                            String l = reader.readLine();
                            Deserializer des = Deserializer.fromOrderResponse(l);
                            if(des.getCode()!=-1) System.out.println("[Server] Stop Order created with id: "+des.getCode());
                            else System.out.println("[Server] Error stop order not created ("+des.getCode()+")");
                        }
                        case 4 -> {
                            String req = InputProcedures.getHistory();
                            writer.println(req);
                            String resp = reader.readLine();
                            ArrayList<Trade> list = Deserializer.fromHistory(resp);
                            System.out.println("[Server] Order History:");
                            for(Trade t: list){
                                System.out.println("["+t.getOrderId()+"] ("+new Date(t.getTimestamp())+") "+t.getOrderType()+" | "+t.getType().toString().toUpperCase()+" - "+(float) t.getPrice() /1000+"$ x "+(float)t.getSize()/1000+"BTC");
                            }

                        }
                        case 5 ->{
                            String req = InputProcedures.cancelOrder();
                            writer.println(req);
                            String resp = reader.readLine();
                            Deserializer response = new Deserializer(resp);
                            System.out.println(response);
                        }
                        case 7 -> {
                            Serializer msg = new Serializer(OperationToken.logout);
                            msg.setLogout();
                            writer.println(msg);
                            Deserializer resp = new Deserializer(reader.readLine());
                            System.out.println(resp);
                            return;
                        }
                        default -> System.out.println("Invalid option, please try again");
                    }
                } catch (NumberFormatException ignored) {System.out.println("invalid input...");}

            }
        }catch (SocketTimeoutException e){
            System.out.println("Connection closed, time out");
        } catch (UnknownJsonObject e){
            System.out.println("[Error] unknown response");
        }catch(ServerSocketClosed e){
            System.out.println("[Error] Connection lost");
        }catch (IOException e){
            System.out.println("[Error] Server connection failed");
        }
    }

    private static void printMenu(){
        System.out.println("Welcome, "+new Date(System.currentTimeMillis()));
        System.out.println("1) Market order");
        System.out.println("2) Limit order");
        System.out.println("3) Stop order");
        System.out.println("4) Order history");
        System.out.println("5) Cancel Order");
        System.out.println("7) Logout");
        System.out.print("Enter choice: ");
    }
}
