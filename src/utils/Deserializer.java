package utils;

import Errors.ServerSocketClosed;
import Errors.UnknownJsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Deserializer{
    private final int code;
    private final String message;

    public Deserializer(String serialized_object) throws UnknownJsonObject,ServerSocketClosed {
        Gson gson = new Gson();
        if(serialized_object == null) throw new ServerSocketClosed();
        JsonObject jsonObject = gson.fromJson(serialized_object, JsonObject.class);
        String code = jsonObject.get("response").getAsString();
        String message = jsonObject.get("errorMessage").getAsString();
        if(code==null || message==null) throw new UnknownJsonObject("invalid server response");
        try{
            this.code = Integer.parseInt(code);
            this.message = message;
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
}
