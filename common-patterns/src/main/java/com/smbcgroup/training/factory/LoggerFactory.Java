package com.smbcgroup.training.factory;

public class LoggerFactory{

    private static Logger logger;

    public static Logger getLogger(){
        if (logger == null)
            logger = new LoggerImpl();
        return logger;

       // return LoggerImpl.getInstance();
    }
}