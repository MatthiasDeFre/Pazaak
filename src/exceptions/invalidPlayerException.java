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
public class invalidPlayerException extends RuntimeException { 

    public invalidPlayerException()
    {
    }

    public invalidPlayerException(String message)
    {
        super(message);
    }

    public invalidPlayerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public invalidPlayerException(Throwable cause)
    {
        super(cause);
    }
    
}
