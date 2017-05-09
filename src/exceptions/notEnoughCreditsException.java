package exceptions;


public class notEnoughCreditsException extends RuntimeException
{

    public notEnoughCreditsException()
    {
    }
     public notEnoughCreditsException(String message){
         super(message);
     }
     
     public notEnoughCreditsException(String message, Throwable cause){
          super(message, cause);
     }
     
     public notEnoughCreditsException(Throwable cause){
         super(cause);
     }

}
