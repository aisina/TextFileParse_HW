package ru.innopolis;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by innopolis on 17.12.2016.
 */
public class TextHandlThreadTest {

    private static Logger log = LoggerFactory.getLogger(TextHandlThreadTest.class);
    private TextHandlThread mythread;
    private static HashSet<String> set;

    @Before
    public void before(){
        log.info("This is @Before method");
        this.mythread = new TextHandlThread(0, "in0.txt", set, true);
    }

    @Test
    public void testParseLine(){
        log.info("Test parseLines method");
        String myString = "test correct string ... ";
        String[] words = this.mythread.parseLine(myString, 3);
        String[] myWords = {"test", "correct", "string", "..."};
        //Массивы нельзя проверить через assertEquals
        assertArrayEquals(words, myWords);
    }

    @Test(expected = ArrayComparisonFailure.class) //ожидаем ошибку
    //ожидаем, что массивы не будут равны, тогда тест б-т пройден
    public void testParseLine2(){
        log.info("Test parseLines method");
        String myString = "Test correct string ... ";
        String[] words = this.mythread.parseLine(myString, 3);
        String[] myWords = {"test", "correct", "string", "..."};

        assertArrayEquals("arrays != ", words, myWords);
    }

    @Test
    public void testParseLine3(){
        log.info("Test parseLines method");
        String myString = "\uFEFFtest correct string ... ";
        String[] words = this.mythread.parseLine(myString, 1);
        String[] myWords = {"test", "correct", "string", "..."};

        assertArrayEquals(words, myWords);
    }
}
