package com.logback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * @DESC Logback explains that having failed to find the logback-test.xml and logback.xml
 *       configuration files (discussed later), it configured itself using its default policy, which
 *       is a basic ConsoleAppender. An Appender is a class that can be seen as an output
 *       destination. Appenders exist for many different destinations including the console, files,
 *       Syslog, TCP Sockets, JMS and many more. Users can also easily create their own Appenders as
 *       appropriate for their specific situation.
 * 
 * 
 * 
 *       logback-core-1.2.3.jar logback-classic-1.2.3.jar logback-examples-1.2.3.jar
 *       slf4j-api-1.7.25.jar
 * 
 * 
 *       Logback is built upon three main classes: Logger, Appender and Layout. These three types of
 *       components work together to enable developers to log messages according to message type and
 *       level, and to control at runtime how these messages are formatted and where they are
 *       reported. The Logger class is part of the logback-classic module. On the other hand, the
 *       Appender and Layout interfaces are part of logback-core. As a general-purpose module,
 *       logback-core has no notion of loggers.
 *
 *
 *       各个logger 都被关联到一个
 *       LoggerContext，LoggerContext负责制造logger，也负责以树结构排列各logger。其他所有logger也通过org.slf4j.LoggerFactory
 *       类的静态方法getLogger取得。 getLogger方法以 logger名称为参数。用同一名字调用LoggerFactory.getLogger
 *       方法所得到的永远都是同一个logger对象的引用。
 * 
 */
public class HelloWorld2 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("com.logback.demo");
        Logger logger1 = LoggerFactory.getLogger("com.logback.demo");
        System.out.println(logger);
        System.out.println(logger == logger1);
        logger.debug("Hello world.");

        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
