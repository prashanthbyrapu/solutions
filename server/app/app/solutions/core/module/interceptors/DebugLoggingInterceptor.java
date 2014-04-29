package app.solutions.core.module.interceptors;

import app.solutions.util.Utility;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * This is the interceptor which is implemented to log debug statements...and method execution time tracing..
 * Created by niveditha on 17/4/14.
 */
public class DebugLoggingInterceptor implements MethodInterceptor {

    private Logger logger = Logger.getLogger(DebugLoggingInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        long start = System.nanoTime();

        logger.debug("Starting Method Call : " + methodInvocation.getMethod().getName());
        logger.debug("Method Arguements: " + Utility.toJson(methodInvocation.getArguments()));

        Object result = methodInvocation.proceed();

        long end = System.nanoTime();
        logger.debug("End of Method call and result is :" + Utility.toJson(result));
        logger.debug("Time Taken for method call is ( milli seconds): " + TimeUnit.NANOSECONDS.convert(end - start, TimeUnit.MILLISECONDS));

        return result;
    }
}
