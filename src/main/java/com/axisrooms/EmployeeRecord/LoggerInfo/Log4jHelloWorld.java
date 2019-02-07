package com.axisrooms.EmployeeRecord.LoggerInfo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4jHelloWorld {

    final static Logger logger = Logger.getLogger(Log4jHelloWorld.class);

    public static void main(String[] args)
    {
        Log4jHelloWorld obj = new Log4jHelloWorld();
        obj.runMe("mkyong");
    }
    private void runMe(String parameter){

        if(logger.isDebugEnabled()){
            logger.debug("This is debug : " + parameter);
        }

        if(logger.isInfoEnabled()){
            logger.info("This is info : " + parameter);
        }

        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);

    }
}

