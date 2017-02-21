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
public class noCorrectNameException extends RuntimeException{
    public noCorrectNameException() {
        
    }
      public noCorrectNameException(String message)
    {
        super(message);
    }
       public noCorrectNameException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public noCorrectNameException(Throwable cause)
    {
        super(cause);
    }

    public noCorrectNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

