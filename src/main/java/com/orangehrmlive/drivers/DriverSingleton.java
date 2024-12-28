package com.orangehrmlive.drivers;


import com.orangehrmlive.drivers.strategies.DriverStrategy;
import com.orangehrmlive.drivers.strategies.DriverStrategyImplementer;
import com.orangehrmlive.utils.Constants;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static DriverSingleton instance = null;
    public static WebDriver driver;

    public DriverSingleton(String driver){
        instantiate(driver);
    }

    public WebDriver instantiate(String strategy){
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(Constants.TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static DriverSingleton getInstance(String driver) {
        if (instance == null) {
            instance = new DriverSingleton(driver);
        }
        return instance;
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void closeObjectInstance(){
        instance = null;
        driver.quit();
    }
}