package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import enums.MarketType;
import enums.OperationToken;

import java.io.Serializable;

/**
 * Oggetto per serializzazione viene passato al costruttore l'operazione
 * che si vuole effettuare e in values si hanno i valori da inserire a seconda
 * dell'operazione che si vuole effettuare.
 */
public class Serializer implements Serializable {
    private final OperationToken operation;
    private JsonObject values=null;

    public Serializer(OperationToken operation) {
        this.operation = operation;
    }

    public void setLogin(String user,String password,int port){
        values = new JsonObject();
        values.addProperty("username",user);
        values.addProperty("password",password);
        values.addProperty("port",port);
    }

    public void setRegister(String user,String password){
        values = new JsonObject();
        values.addProperty("username",user);
        values.addProperty("password",password);
    }

    public void updateCredentials(String user,String old_pass, String new_pass){
        values = new JsonObject();
        values.addProperty("username",user);
        values.addProperty("oldPassword",old_pass);
        values.addProperty("newPassword",new_pass);
    }
    public void setLogout(){
        values = new JsonObject();
    }
    public void setLimitStop(MarketType opType,int size,int price){
        values = new JsonObject();
        values.addProperty("type",opType.toString());
        values.addProperty("size",size);
        values.addProperty("price",price);
    }
    public void setMarket(MarketType opType,int size){
        values = new JsonObject();
        values.addProperty("type",opType.toString());
        values.addProperty("size",size);
    }

    public void setOrderCancel(int id){
        values = new JsonObject();
        values.addProperty("orderId",id);
    }
    public void setHistory(String month){
        values = new JsonObject();
        values.addProperty("month",month);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        if(values==null) throw new RuntimeException("Serializer empty field values...");
        return gson.toJson(this);
    }
}
