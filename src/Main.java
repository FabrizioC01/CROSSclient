import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import utils.Deserializer;
import utils.InputProcedures;
import utils.PropertiesManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;

import java.net.SocketTimeoutException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        PropertiesManager prop = new PropertiesManager();

        try(Socket socket = new Socket(prop.getAddress(),prop.getPort());
            DatagramSocket udp = new DatagramSocket();
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

            System.out.println("+++ CROSS +++");
            System.out.println("1) login\n2) update credentials\n3) register\nx) exit");

            Scanner scanner = new Scanner(System.in);

            int val=-1;
            try {
                val = Integer.parseInt(scanner.nextLine());
                switch (val){
                    case 1->{
                        String res = InputProcedures.login(udp.getLocalPort());
                        writer.println(res);
                        Deserializer resp = new Deserializer(reader.readLine());
                        System.out.println(resp);
                        if(resp.getCode()==100) ReservedArea.init(socket,writer,reader,scanner);
                    }
                    case 2->{
                        String res = InputProcedures.updateCredentials();
                        writer.println(res);
                        Deserializer resp = new Deserializer(reader.readLine());
                        System.out.println(resp);
                    }
                    case 3->{
                        String res = InputProcedures.register();
                        writer.println(res);
                        Deserializer resp = new Deserializer(reader.readLine());
                        System.out.println(resp);
                    }
                    default -> {
                        socket.close();
                    }
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }

            System.out.println("bye...");
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