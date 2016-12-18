package ru.innopolis;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by innopolis on 17.12.2016.
 */
public class ReaderClass {

    /**
     *
     * @param fileName
     * @param cs
     * @return
     * @throws FileNotFoundException
     * считывает файл и записывает в BufferedReader
     */
    public static BufferedReader readUsingBufferedReader(String fileName, Charset cs) throws FileNotFoundException {

        File file = new File(fileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
            //e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis, cs);
        BufferedReader br = new BufferedReader(isr);
        return br;
    }
}
