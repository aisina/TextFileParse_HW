package ru.innopolis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

/**
 * Created by innopolis on 10.12.2016.
 */
public class TextHandlThread extends Thread{
    private static Logger logger = LoggerFactory.getLogger(TextHandlThread.class);
    public ValidatorImpl validator = new ValidatorImpl();

    int id;
    String fileName;
    HashSet<String> mySet;
    static Boolean isWorkingThreads;

    TextHandlThread(int id, String fileName, HashSet mySet, Boolean isWorkingThreads){
        this.id = id;
        this.fileName = fileName;
        this.mySet = mySet;
        this.isWorkingThreads = isWorkingThreads;
    }

    @Override
    public void run() {
        ReaderClass reader = new ReaderClass();
        try (BufferedReader br = reader.readUsingBufferedReader(this.fileName, StandardCharsets.UTF_8);) {

            String line;
            String[] words;

            while(isWorkingThreads){

                //считываем, пока не будет достигнут конец файла
                // line.length() != 0 - случай, когда строка пуста (лишний Enter)
                int linenumber = 0;
                while((line = br.readLine()) != null  && line.length() != 0 && isWorkingThreads) {

                    linenumber++;

                    words = parseLine(line, linenumber);

                    int i = 0;
                    while (i < words.length && isWorkingThreads) {

                        isWorkingThreads = validator.validate(words[i]);

                        if (isWorkingThreads) {

                            synchronized (mySet) {

                                if(mySet.contains(words[i])){
                                    logger.warn("В файле '" + this.fileName + "' найден дубликат слова '" + words[i] + "'");
                                    isWorkingThreads = false;
                                    break;
                                }
                                else{
                                    mySet.add(words[i]);
                                }
                                i++;
                            }
                        } else {
                            logger.warn("Слово '" + words[i] + " в файле '" + this.fileName + "' не является корректным");
                            break;
                        }
                    }
                }//end of while i < length
            }//end of while(! isWorkingThread )
            br.close();
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            logger.warn("Файл '" + this.fileName + "' не найден");
        } catch (IOException e) {
            //e.printStackTrace();
            logger.warn("Ошибка чтения файла '" + this.fileName + "'");
        }
    }

    /**
     *
     * @param line
     * @param lineNumber
     * @return array of words (split line)
     */

    public static String[] parseLine(String line, int lineNumber){
        String[] words = line.trim().split(" ");

        if(lineNumber == 1)
            words[0] = words[0].substring(1);// //UTF-8 в начало файла (в начало первого слова текста) добавлется маркер последовательности байтов. Его исключаем

        return words;
    }
}