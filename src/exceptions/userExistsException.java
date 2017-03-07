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
public class userExistsException extends RuntimeException{
      public userExistsException() {
        
    }
      public userExistsException(String message)
    {
        super(message);
    }
       public userExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public userExistsException(Throwable cause)
    {
        super(cause);
    }

    public userExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
