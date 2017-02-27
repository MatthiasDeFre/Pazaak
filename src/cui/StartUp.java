package cui;
import domain.DomainController;

public class StartUp
{
    public static void main(String[] args)
    {
        DomainController dc = new DomainController();
        cui cui = new cui(dc);
        cui.startRegistration();
    }
}
