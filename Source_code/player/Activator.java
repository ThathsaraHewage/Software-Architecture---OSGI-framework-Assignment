package player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import gameservice.service.GameService; 

public class Activator implements BundleActivator
{
    private BundleContext context = null;
    private ServiceTracker tracker = null;

    public void start(BundleContext context) throws Exception
    {
        context = context;

        tracker = new ServiceTracker(
            context,
            context.createFilter("(&(objectClass=" + GameService.class.getName() + ")" +"(Language=*))"),null);
            tracker.open();

        try
        {
            //display exit method of the game
            System.out.println("TO EXIT :: Enter blank > ");
            String number = "";
        
              BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

                        //loop until player exit the game
                        while (true){

                            /*
                            * prompt player to enter a guessing number between zero to ten
                            */    
                            System.out.print("Enter a number :: Between 10-0 > ");
                            number = in.readLine();

                            GameService game = (GameService)tracker.getService();

                            /*
                            * check the player going to exit or not
                            */
                            if (number.length() == 0){
                                break;
                            }
                            else if (game == null){
                                /*
                                * display this statement if the game service is not registered / available
                                */
                                System.out.println("OOPs..Game service is not available !");
                            }
                            else if (game.checkNumber(number)){
                                /*
                                * display this statement if the player entered number is matched
                                */
                                System.out.println("Congratulations...you are win !!! ");
                            }
                            else
                            {
                                /*
                                * display this statement if the player entered number is not matched
                                */
                                System.out.println("oopsss...you missed your chance..");
                            }
                        }
            } 
        catch (Exception ex) { 
        
        }
    }
    /*
    *this method stop the player bundle
    */
    public void stop(BundleContext context){}
}