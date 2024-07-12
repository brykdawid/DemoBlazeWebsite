package javaautomation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private static List<String> emailList = new ArrayList<>();
    private static List<String> contactNameList = new ArrayList<>();
    private static List<String> messageList = new ArrayList<>();

    private static int currentIndexEmail = 0;
    private static int currentIndexContactName = 0;
    private static int currentMessage = 0;

    public static void fileReader(String fileName, List<String> list) throws IOException {
        ClassLoader classLoader = FileReader.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("Niepoprawne dane, nie znaleziono pliku " + fileName);
            }
            list.addAll(bufferedReader.lines().toList());
        }

    }
    public synchronized String getNextEmail() throws IOException{
        if(emailList.isEmpty()){
            fileReader("Email", emailList);
        }
        String getEmail = emailList.get(currentIndexEmail);
        currentIndexEmail = (currentIndexEmail+1)%emailList.size();
        return getEmail;
    }
    public synchronized String getNextContactName() throws IOException{
        if(contactNameList.isEmpty()){
            fileReader("ContactName", contactNameList);
        }
        String nextContactName = contactNameList.get(currentIndexContactName);
        currentIndexContactName = (currentIndexContactName+1)%contactNameList.size();
        return nextContactName;
    }
}

