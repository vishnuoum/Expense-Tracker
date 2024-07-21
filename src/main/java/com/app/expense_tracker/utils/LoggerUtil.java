package com.app.expense_tracker.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    public static void printInfo(Class clazz, Object object) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(object.toString());
    }

    public static void printError(Class clazz, Object object) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(object.toString());
    }
}
