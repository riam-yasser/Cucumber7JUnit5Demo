package com.example.actions;

import com.example.locators.HomePageLocators;
import com.example.utils.HelperClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePageActions {

    HomePageLocators homePageLocators = null;

    public HomePageActions(){
        this.homePageLocators = new HomePageLocators();
        PageFactory.initElements(HelperClass.getDriver(), homePageLocators);
    }

    //Get Username From Home Page
    public WebElement getHomePageText(){
        return homePageLocators.homePageUserName;
    }
}
