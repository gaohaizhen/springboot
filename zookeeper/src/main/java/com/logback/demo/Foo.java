package com.logback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {
    private static Logger log = LoggerFactory.getLogger(Foo.class);
    public void doIt() {
        log.debug("Foo dpIt");
    }

}
