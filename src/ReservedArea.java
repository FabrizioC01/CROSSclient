import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import enums.OperationToken;
import utils.Deserializer;
import utils.Serializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ReservedArea {
    private static  Socket socket=null;
    private static PrintWriter writer=null;
    private static BufferedReader reader=null;

    public static void init(Socket sock, PrintWriter write, BufferedReader read) {
        socket=sock;
        writer=write;
        reader=read;
        System.out.println("Welcome select one operation:");
        System.out.println("1)Market order\n2)Limit order\n3)Stop order\n4)Order history\n5)Book\n6)My orders\n7)Logout");
        Scanner in = new Scanner(System.in);
        try{
            while (true) {

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
}
