package io_streams_exercises;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ConsoleTextEditor {

    private File fileToWrite;

    public ConsoleTextEditor(File fileToWrite) {
        this.fileToWrite = fileToWrite;
    }

    public void start(){
        System.out.println("Please input text: ");
        String result = getInput();

        saveTextToFile(result);

        System.out.println(readFile());


    }


    public String getInput(){
        Scanner scanner = new Scanner(System.in);
        String input;
        StringBuilder result = new StringBuilder();
        while(true){
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")){
                break;
            }
            result.append(input).append(System.lineSeparator());
        }
        return result.toString();
    }

    public void saveTextToFile(String text){
        try {
            Files.write(fileToWrite.toPath(), text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(){
        try {
            return Files.readString(fileToWrite.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}