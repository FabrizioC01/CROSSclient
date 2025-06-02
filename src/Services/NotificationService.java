package Services;

import Models.Notification;
import utils.Deserializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class NotificationService implements Runnable {
    private static DatagramSocket socket=null;
    private static boolean canRun = true;

    public NotificationService(DatagramSocket ds) {
        socket = ds;
    }

    /**
     * Resta in ascolto (sulla porta aperta e comunicata al login)
     * di notifiche dal server.
     */
    @Override
    public void run() {
        byte[] buffer = new byte[2048];
        try{
            while(canRun) {
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);
                String msg = new String(data.getData(), 0, data.getLength());
                Notification not = Deserializer.fromNotificationResponse(msg);
                System.out.println();
                not.printNotifications();
            }
        }catch (SocketException ex){
            if(canRun) System.out.println("[Notifications] Notification service error");
            else System.out.println("[Notifications] Notification service stopped");
        } catch (IOException e) {
            System.out.println("[Notifications] Notification Service Error");
        }

    }

    /**
     * Ferma il servizio in ascolto e imposta canRun a false
     * solo per l'output
     */
    public static void stop() {
        canRun = false;
        socket.close();
    }
}
