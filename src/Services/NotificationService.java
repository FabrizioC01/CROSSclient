package Services;

import Models.Notification;
import utils.Deserializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class NotificationService implements Runnable {
    private final DatagramSocket socket;

    public NotificationService(DatagramSocket ds) {
        this.socket = ds;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[2048];
        try{
            while(true) {
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);
                String msg = new String(data.getData(), 0, data.getLength());
                Notification not = Deserializer.fromNotificationResponse(msg);
                System.out.println();
                not.printNotifications();
            }
        }catch (IOException e) {
            System.out.println("[Notifications] Error in notification thread (stopped)");
        }

    }
}
