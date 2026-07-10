package sdet.reporting;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;

import java.net.ConnectException;
import java.sql.SQLSyntaxErrorException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AllureCategoriesTest {

    @Test
    void assertionFailure(){
        assertEquals(10,20,"Assertion failure");
    }

    @Test
    void timeoutFailure() throws TimeoutException {
        throw new TimeoutException("timeout");
    }

    @Test
    void staleElementFaillure(){
        throw new StaleElementReferenceException("stale element");
    }

    @Test
    void noSuchElementFailure(){
        throw new NoSuchElementException("element not found");
    }

    @Test
    void clickInterceptedFailure(){
        throw new ElementClickInterceptedException("button covered");
    }

    @Test
    void browserLaunchFailure(){
        throw new SessionNotCreatedException("Browser version mismatch");
    }

    @Test
    void sqlFailure() throws Exception{
        throw new SQLSyntaxErrorException("SQL syntax error");
    }

    @Test
    void connectionFailure() throws Exception{
        throw new ConnectException("connection reset");
    }

    @Test
    void flakyFailure(){
        throw new RuntimeException("timeout");
    }

    @Test
    void genericFailure(){
        fail("Random product defect");
    }

    @Test
    @Disabled("Skipping this test for demo")
    void skippedTest() {

    }

    @Test
    @Disabled("Skipping this test for demo")
    void skippedTestTwo() {

    }
}
