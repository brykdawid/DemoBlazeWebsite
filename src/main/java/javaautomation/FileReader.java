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
    private static List<String> cityList = new ArrayList<>();
    private static List<String> countryList = new ArrayList<>();
    private static List<String> creditcardList = new ArrayList<>();
    private static List<String> monthList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();
    private static List<String> yearList = new ArrayList<>();
    private static List<String> usernameList = new ArrayList<>();
    private static List<String> passwordList = new ArrayList<>();

    private static int currentIndexEmail = 0;
    private static int currentIndexContactName = 0;
    private static int currentIndexMessage = 0;
    private static int currentIndexCity = 0;
    private static int currentIndexCountry = 0;
    private static int currentIndexCreditcard = 0;
    private static int currentIndexMonth = 0;
    private static int currentIndexName = 0;
    private static int currentIndexYear = 0;
    private static int currentIndexUsername = 0;
    private static int currentIndexPassword = 0;


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
    public static synchronized String getNextEmail() throws IOException{
        if(emailList.isEmpty()){
            fileReader("Email", emailList);
        }
        String getEmail = emailList.get(currentIndexEmail);
        currentIndexEmail = (currentIndexEmail+1)%emailList.size();
        return getEmail;
    }
    public static synchronized String getNextContactName() throws IOException{
        if(contactNameList.isEmpty()){
            fileReader("ContactName", contactNameList);
        }
        String nextContactName = contactNameList.get(currentIndexContactName);
        currentIndexContactName = (currentIndexContactName+1)%contactNameList.size();
        return nextContactName;
    }
    public static synchronized String getNextMessage() throws IOException{
        if(messageList.isEmpty()){
            fileReader("Message", messageList);
        }
        String nextMessage = messageList.get(currentIndexMessage);
        currentIndexMessage = (currentIndexMessage+1)%messageList.size();
        return nextMessage;
    }
    public static synchronized String getNextCity() throws IOException{
        if(cityList.isEmpty()){
            fileReader("City", cityList);
        }
        String nextCity = cityList.get(currentIndexCity);
        currentIndexCity = (currentIndexCity+1)%cityList.size();
        return nextCity;
    }
    public static synchronized String getNextCountry() throws IOException{
        if(countryList.isEmpty()){
            fileReader("Country", countryList);
        }
        String nextCountry = countryList.get(currentIndexCountry);
        currentIndexCountry = (currentIndexCountry+1)%countryList.size();
        return nextCountry;
    }
    public static synchronized String getNextCreditcard () throws IOException{
        if(creditcardList.isEmpty()){
            fileReader("Creditcard", creditcardList);
        }
        String nextCreditcard = creditcardList.get(currentIndexCreditcard);
        currentIndexCreditcard = (currentIndexCreditcard+1)%creditcardList.size();
        return nextCreditcard;
    }
    public static synchronized String getNextMonth() throws IOException{
        if(monthList.isEmpty()){
            fileReader("Month", monthList);
        }
        String nextMonth = monthList.get(currentIndexMonth);
        currentIndexMonth = (currentIndexMonth+1)%monthList.size();
        return nextMonth;
    }
    public static synchronized String getNextName () throws IOException{
        if(nameList.isEmpty()){
            fileReader("Name", nameList);
        }
        String nextName = nameList.get(currentIndexName);
        currentIndexName = (currentIndexName+1)%nameList.size();
        return nextName;
    }
    public static synchronized String getNextYear() throws IOException {
        if(yearList.isEmpty()){
            fileReader("Year", yearList);
        }
        String nextYear = yearList.get(currentIndexYear);
        currentIndexYear=(currentIndexYear+1)%yearList.size();
        return nextYear;
    }
    public static synchronized String getNextUsername() throws IOException{
        if(usernameList.isEmpty()){
            fileReader("Username", usernameList);
        }
        String nextUsername = usernameList.get(currentIndexUsername);
        currentIndexUsername = (currentIndexUsername+1)%usernameList.size();
        return nextUsername;
    }
    public static synchronized String getNextPassword() throws IOException{
        if(passwordList.isEmpty()){
            fileReader("Password", passwordList);
        }
        String nextPassword = passwordList.get(currentIndexPassword);
        currentIndexPassword = (currentIndexPassword+1)%passwordList.size();
        return nextPassword;
    }
}

