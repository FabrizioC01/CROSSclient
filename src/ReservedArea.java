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
    public static void init(Socket socket, PrintWriter write, BufferedReader read) {
        System.out.println("Welcome select one operation:");
        System.out.println("1)Market order\n2)Limit order\n3)Stop order\n4)Order history\n5)Book\n6)My orders\n7)Logout");
        Scanner in = new Scanner(System.in);
        try{
            while (true) {
                if(in.hasNextInt()) {
                    int val = in.nextInt();
                    if(val <1 || val > 6) {
                        System.out.println("Invalid input");
                        continue;
                    }
                    switch(val) {
                        case 9->{
                            Serializer rq = new Serializer(OperationToken.logout);
                            rq.setLogout();
                            write.println(rq);
                            Deserializer resp = new Deserializer(read.readLine());
                            System.out.println(resp);
                            return;
                        }
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
        }
    }
}
