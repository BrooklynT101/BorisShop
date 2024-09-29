///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package web;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
///**
// *
// * @author Brooklyn
// */
//public class SignInWebTest {
//
////    private Selenium config;
//    private WebDriver drvr;
//
//    @BeforeEach
//    public void setUp() {
////        config = new SeleniumConfig();
////        drvr = config.getDriver();
//    }
//
//    @Test
//    public void testSigninFormLayout() {
//// load the signin page in the browser
//        drvr.get("http://www.localhost:8080/sign-in.html");
//
//// check that all elements of the sign in form are present
//        assertThat(drvr.findElement(By.id("username")).isDisplayed(), is(true));
//        assertThat(drvr.findElement(By.id("password")).isDisplayed(), is(true));
//        assertThat(drvr.findElement(By.id("submit")).isDisplayed(), is(true));
//    }
//
//    @Test
//    public void testSignin() {
//        drvr.get("http://www.localhost:8080/sign-in.html");
//
////sign in
//        drvr.findElement(By.id("username")).sendKeys("boris");
//        drvr.findElement(By.id("password")).sendKeys("password");
//        drvr.findElement(By.id("submit")).click();
//
//// check result
//        assertThat(drvr.findElement(By.id("welcome")).getText(), is("Welcome Boris"));
//        
//        
//    }
//}
