package ru.innopolis;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by innopolis on 17.12.2016.
 */
public class ValidatorImplTest {

    private static Logger log = LoggerFactory.getLogger(ValidatorImplTest.class);
    private ValidatorImpl validator;

    @Before
    public void before(){
        log.info("This is @Before method");
        this.validator = new ValidatorImpl();
    }

    @Test
    public void validateCorrect(){
        log.info("Test validateCorrect method");
        boolean bool = this.validator.validate("Корректное");
        assertTrue("Incorrect word", bool == true);
    }

    @Test
    public void validateIncorrect(){
        log.info("Test validateIncorrect method");
        boolean bool = this.validator.validate("Неkорректное");
        assertTrue("Incorrect word", bool == false);
    }

}
