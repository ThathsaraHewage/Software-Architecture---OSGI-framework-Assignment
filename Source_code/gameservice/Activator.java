//WD_33
//Service Implemented by IT19220116 _ N.I.T.Hewage

package gameservice;

import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceEvent;
import gameservice.service.GameService;

public class Activator implements BundleActivator
{
    
    public void start(BundleContext context)
    {
        Hashtable<String, String> props = new Hashtable<String,String>();
        props.put("Language", "English");
        context.registerService(GameService.class.getName(), new GameImpl(), props);

        /*
        * This shows the status of the registration of the game service 
        */
	    System.out.println("Number guessing game service registered & started successfully.....");		
    }

    /* The service is automatically unregistered */
    public void stop(BundleContext context){}

    private static class GameImpl implements GameService
    {
        /*
        * Matching numbers to win the game  
        */
        String[] lucky_numbers ={ "two", "ten", "zero" };

        //this method implements from the GameService interface
        public boolean checkNumber(String number)
        {
            //convert the number letters to simple letters
            number = number.toLowerCase();

            for (int i = 0; i < lucky_numbers.length; i++)
            {
                if (lucky_numbers[i].equals(number))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
//end of the activator class