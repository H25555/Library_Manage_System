package org.example.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVUltis {
    public static <T> void write(String filename, List<T> items){
        try {
            PrintWriter print = new PrintWriter(filename);
            for (Object item: items) {
                print.println(item.toString());
            }
            print.flush();
            print.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(filename + "invalid");
        }
    }
    public static List<String>read(String path){
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e){
            throw new IllegalArgumentException(path + "invalid");
        }
    }

}
