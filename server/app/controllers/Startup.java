package controllers;

import app.solutions.core.bootstrap.Bootstrap;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 * Created by niveditha on 12/4/14.
 */
@OnApplicationStart
public class Startup  extends Job{
    @Override
    public void doJob() {
        Logger.info("In Starup Job");
        Bootstrap.getInstance().bootstrap();
    }
}
