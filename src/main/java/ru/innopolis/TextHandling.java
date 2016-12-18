package ru.innopolis;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by innopolis on 10.12.2016.
 */
public class TextHandling {

    /**
     * @param dir
     * @param textNames
     * получение списка всех txt файлов в указанном dir е, запись в textNames
     */
    public static void getFilesFromClasspath(File dir, List<String> textNames){
        // если объект представляет каталог
        int counter = 0;
        if(dir.isDirectory()){
            // получаем все вложенные объекты в каталоге
            for(File item : dir.listFiles()){
                if(!item.isDirectory()){
                    //нужны только txt файлы
                    if(item.getName().substring(item.getName().length()-3).equals("txt")){
                        textNames.add(counter, item.getName());
                        counter++;
                    }
                }
            }
        }
    }

    /**
     *
     * @param threads
     * запуск всех потоков
     */
    public static void startThreads(Thread[] threads){
        for(int i = 0; i < threads.length; i++){
            threads[i].start();
        }
    }

    public static volatile Boolean isWThreads = new Boolean(true);
    private static HashSet<String> set = new HashSet<String>(); // общий объект, монитор

    public static void main(String[] args) {

        List<String> textNames = new ArrayList<String>(); //названия файлов

        File dir = new File("C:\\Users\\innopolis\\IdeaProjects\\TextFileParse_HW"); //в данной папке хранятся txt файлы
        getFilesFromClasspath(dir, textNames);

        //массив Threads для каждого каталога
        Thread[] threads = new Thread[textNames.size()];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new TextHandlThread(i, textNames.get(i), set);
        }

        startThreads(threads);

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        }
    }

