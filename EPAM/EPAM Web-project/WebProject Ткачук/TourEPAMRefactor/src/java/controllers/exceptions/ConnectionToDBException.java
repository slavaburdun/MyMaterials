package controllers.exceptions;

public class ConnectionToDBException extends Exception {

    private static final String msg = "Can not connect to DB";
    
    public ConnectionToDBException(){
        super(msg);
    }
}
