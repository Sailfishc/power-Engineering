package com.sailfish.engineering.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sailfish
 * @create 2019-10-17-10:15
 */
public class Slf4jLog4jDemo {

    final static Logger logger = LoggerFactory.getLogger(Slf4jLog4jDemo.class);


    public static void main(String[] args) {
        logger.info("info level");
        logger.debug("debug level");
//        logger.error("error level");
    }
}
