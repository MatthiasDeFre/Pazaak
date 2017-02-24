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
public class noCorrectBirthyearException extends RuntimeException{
    public noCorrectBirthyearException() {
        
    }
      public noCorrectBirthyearException(String message)
    {
        super(message);
    }
       public noCorrectBirthyearException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public noCorrectBirthyearException(Throwable cause)
    {
        super(cause);
    }

    public noCorrectBirthyearException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

