package ru.innopolis;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by innopolis on 17.12.2016.
 */
public class ReaderClassTest {

    ReaderClass reader = new ReaderClass();

    @Test(expected = FileNotFoundException.class)
    public void reader() throws FileNotFoundException{
        String path = "C:\\Users\\innopolis\\IdeaProjects\\TextFileParse_HW\\not_exists.txt";
        this.reader.readUsingBufferedReader(path, StandardCharsets.UTF_8);
    }

    @Test()
    public void reader2() throws FileNotFoundException {
        String path = "C:\\Users\\innopolis\\IdeaProjects\\TextFileParse_HW\\in0.txt";
        this.reader.readUsingBufferedReader(path, StandardCharsets.UTF_8);
    }


}
