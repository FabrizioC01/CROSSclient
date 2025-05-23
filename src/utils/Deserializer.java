package utils;

import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;


public class Deserializer implements Serializable {
    private final int code;
    private final String message;

    public Deserializer(int code, String message) {
        this.code = code;
        this.message = message;
    }

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
