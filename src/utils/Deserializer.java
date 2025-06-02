package utils;

import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import Models.Notification;
import Models.Trade;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;



public class Deserializer implements Serializable {
    private final int code;
    private final String message;

    /**
     * Creea un messaggio di errore
     * @param code codice di risposta
     * @param message risposta
     */
    public Deserializer(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *  Deserializza messaggi del server per operazioni che ricevono
     *  codici e messaggi di risposta.
     * @param serialized_object Stringa ricevuta dal server da de-serializzare
     * @throws UnknownJsonObject Sollevata quando la risposta del server non è riconosciuta
     * @throws ServerSocketClosed Il server ha chiuso la connessione
     */
    public Deserializer(String serialized_object) throws UnknownJsonObject,ServerSocketClosed {
        Gson gson = new Gson();
        if(serialized_object == null) throw new ServerSocketClosed();
        JsonObject jsonObject = gson.fromJson(serialized_object, JsonObject.class);
        JsonElement je1= jsonObject.get("response");
        JsonElement je2= jsonObject.get("errorMessage");
        if(je1==null || je2==null) throw new UnknownJsonObject("invalid server response");
        String code = je1.getAsString();
        String message = je2.getAsString();
        try{
            this.code = Integer.parseInt(code);
            this.message = message;
        }catch (NumberFormatException e){
            throw new UnknownJsonObject("invalid server response");
        }
    }

    /**
     *  Deserializza risposte dal market con i codici degli ordini
     * @param serialized_object oggetto
     * @return Istanza Deserializer con code = orderId
     * @throws UnknownJsonObject Messaggio sconosciuto
     * @throws ServerSocketClosed Connessione chiusa
     */
    public static Deserializer fromOrderResponse(String serialized_object) throws UnknownJsonObject,ServerSocketClosed {
        Gson gson = new Gson();
        if(serialized_object==null) throw new ServerSocketClosed();
        JsonObject jsonObject = gson.fromJson(serialized_object, JsonObject.class);
        JsonElement code = jsonObject.get("orderId");
        if(code==null) throw new UnknownJsonObject("invalid server response");
        try{
            int val = Integer.parseInt(code.getAsString());
            return new Deserializer(val,null);
        }catch (NumberFormatException e){
            throw new UnknownJsonObject("invalid server response");
        }
    }

    /**
     * Restituisce in un array lo storico degli ordini ricevuti dal server.
     * @param serialized_object Oggetto serializzato dal server
     * @return Lista degli ordini
     * @throws UnknownJsonObject Formato non riconosciuto
     * @throws ServerSocketClosed Connessione chiusa
     */
    public static ArrayList<Trade> fromHistory(String serialized_object) throws UnknownJsonObject,ServerSocketClosed {
        Gson gson = new Gson();
        if(serialized_object==null) throw new ServerSocketClosed();
        JsonObject jsonObject = gson.fromJson(serialized_object, JsonObject.class);
        JsonElement je1= jsonObject.get("trades");
        if(je1==null) throw new UnknownJsonObject("invalid server response");
        return gson.fromJson(je1.getAsJsonArray(), new TypeToken<ArrayList<Trade>>(){}.getType());
    }

    /**
     * Deserializza le notifiche in entrata
     * @param serialized_object Notifica serializzata
     * @return Oggetto Notification
     */
    public static Notification fromNotificationResponse(String serialized_object){
        Gson gson = new Gson();
        return gson.fromJson(serialized_object, Notification.class);
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[Server] "+message+" ("+code+")";
    }
}
