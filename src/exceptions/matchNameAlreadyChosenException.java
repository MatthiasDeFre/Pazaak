/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Matthias
 */
public class matchNameAlreadyChosenException extends RuntimeException{
     public matchNameAlreadyChosenException() {
        
    }
      public matchNameAlreadyChosenException(String message)
    {
        super(message);
    }
       public matchNameAlreadyChosenException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public matchNameAlreadyChosenException(Throwable cause)
    {
        super(cause);
    }

    public matchNameAlreadyChosenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    } 
}
