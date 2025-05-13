import utils.PropertiesManager;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        PropertiesManager prop = new PropertiesManager();
        try(Socket sock = new Socket(prop.getAddress(),prop.getPort());
            DatagramSocket upd = new DatagramSocket()) {

        }catch (IOException e){
            System.out.println("Server connection failed");
        }
    }
}