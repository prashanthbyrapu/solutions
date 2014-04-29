package app.solutions.core.bootstrap;

import app.solutions.core.module.CoreModule;
import app.solutions.core.service.DocumentReadService;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.logging.Logger;

/**
 * Created by niveditha on 12/4/14.
 */
public class Bootstrap {

    private Logger logger = Logger.getLogger("BootStrap");

    Injector injector;

    private static Bootstrap bootstrap = new Bootstrap();
    public static Bootstrap getInstance(){
        return bootstrap;
    }

    public  void bootstrap(){

        logger.info("In Bootstrap");
        // Create GUICE injector.
        injector = Guice.createInjector(new CoreModule());

        logger.info("Done! Bootstrapping");

    }


    public Object getService(Class serviceClass){
        return injector.getInstance(serviceClass);
    }


    public static void main(String[] args){
        Bootstrap.getInstance().bootstrap();
        Bootstrap.getInstance().getService(DocumentReadService.class);
    }
}
