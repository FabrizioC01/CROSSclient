import utils.PropertiesManager;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        PropertiesManager prop = new PropertiesManager();

        try(Socket socket = new Socket(prop.getAddress(),prop.getPort());
            DatagramSocket upd = new DatagramSocket()) {
            while(true) {
                System.out.println("+++ CROSS +++");
                System.out.println("1) login\n2) update credentials\n3) register\nx) exit");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                //QUI - QUI - QUI - QUI - QUI
            }


        }catch (IOException e){
            System.out.println("Server connection failed");
        }
    }
}