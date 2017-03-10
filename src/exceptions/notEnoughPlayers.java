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
public class notEnoughPlayers extends RuntimeException{

    public notEnoughPlayers()
    {
    }

    public notEnoughPlayers(String message)
    {
        super(message);
    }

    public notEnoughPlayers(String message, Throwable cause)
    {
        super(message, cause);
    }

    public notEnoughPlayers(Throwable cause)
    {
        super(cause);
    }
    
}
