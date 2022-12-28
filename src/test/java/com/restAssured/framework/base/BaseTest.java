package com.restAssured.framework.base;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method m){

        System.out.println("STARTING TEST: ----> " + m.getName() + "THREAD ID: ----> " + Thread.currentThread().getId());


    }
}
