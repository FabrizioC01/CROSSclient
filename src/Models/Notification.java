package Models;

import enums.MarketType;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Notification {
    private String notification;
    private ArrayList<Trade> trades;

    public void printNotifications(){
        System.out.println("[Notification] type : "+notification);
        for(Trade t : trades){
            Date d = new Date(t.getTimestamp());
            if(t.getOrderId()==-1) System.out.println("[FAILED] ("+d+") "+t.getOrderType()+" | "+t.getType().toString().toUpperCase()+" - "+(float) t.getPrice() /1000+"$ x "+(float)t.getSize()/1000+"BTC");
            else System.out.println("["+t.getOrderId()+"] ("+d+") "+t.getOrderType()+" | "+t.getType().toString().toUpperCase()+" - "+(float) t.getPrice() /1000+"$ x "+(float)t.getSize()/1000+"BTC");
        }
    }
}

