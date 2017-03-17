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
public class notEnoughPlayersException extends RuntimeException{

    public notEnoughPlayersException()
    {
    }

    public notEnoughPlayersException(String message)
    {
        super(message);
    }

    public notEnoughPlayersException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public notEnoughPlayersException(Throwable cause)
    {
        super(cause);
    }
    
}
