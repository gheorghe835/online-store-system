package main.java.com.store.exception;

/*
StoreException este excepția de bază a sistemului
de magazin online și extinde clasa Exception,
fiind o excepție checked. Ea este folosită
pentru a trata erorile generale care apar în cadrul aplicației.
 */

public class StoreException extends  Exception{
    public StoreException(String message){
        super(message);
    }

    public StoreException(String message,
                          Throwable cause){
        super(message, cause);
    }
}
