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
public class matchNotFoundException extends RuntimeException{
     public matchNotFoundException()
    {
    }
     public matchNotFoundException(String message){
         super(message);
     }
     
     public matchNotFoundException(String message, Throwable cause){
          super(message, cause);
     }
     
     public matchNotFoundException(Throwable cause){
         super(cause);
     }

}
