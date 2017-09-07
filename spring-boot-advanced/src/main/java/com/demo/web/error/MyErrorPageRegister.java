package com.demo.web.error;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MyErrorPageRegister implements ErrorPageRegistrar {
    /**
     * Register pages as required with the given registry.
     *
     * @param registry the error page registry
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        ErrorPage pageNotFound = new ErrorPage(HttpStatus.NOT_FOUND,"/user/getUserInfo.do");

        registry.addErrorPages(pageNotFound);
    }
}
