import  java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    protected static List<String> gameDataList= new ArrayList<>();//list to save game data
    protected static String[][] GamesArray; //array to store game data in table
    public static boolean LogInData (String username, String password)//to log in using accounts saved in the file
    {
        boolean isLoggedIn = false;
        try {
            File myObj = new File("resources/accounts");
            Scanner myReader;
            myReader = new Scanner(myObj);
            // take from file

            while (myReader.hasNextLine()) {
                //loop in which data of each line in file "account" is saved in string
                String theLine = myReader.nextLine();
                if (!theLine.isEmpty()) {
                    // split data of line in the string and put it in an array
                    String[] userData = theLine.split("%");
                    String storedUsername = userData[0];
                    String storedPassword = userData[1];
                    // compare between saved data in the file and data entered by the user
                    if (storedUsername.equals(username)) {
                        if (storedPassword.equals(password)) {
                            isLoggedIn = true;
                            break;
                        }
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException ep) {
            ep.printStackTrace();
        }
        return isLoggedIn;
    }
    //  resources/judy
    public static void WriteToFile(String gameData){
        try {
            String user = login_page.user;
            // take username entered in string
            String filepath = "resources/";
            FileWriter writer = new FileWriter(filepath+user, true);
            writer.write(  gameData+"\n"); // go to the file specified by username using file path and write in it game data entered(players+timer)
            writer.close();
        } catch (IOException evt) {
            throw new RuntimeException(evt);
        }
    }

    public static void ReadDataFromFile(){
        try {
            String filePath="resources/";
            String user = login_page.user;
            File myObj = new File(filePath+user);// go to the specified file & take data from it
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String gameDataLine = myReader.nextLine();
                String[] gameDataLineSplit = gameDataLine.split("%");// save the data of each game"which is a line" in string
                for (int i=0; i< gameDataLineSplit.length; i++) {
                    gameDataList.add(gameDataLineSplit[i]);
                }// split the string into elements and save it in list
//
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numberOfGames=0;
        int listSize= gameDataList.size();
        while(listSize%5==0 && listSize!=0){// calc n. of games by dividing elements of list by 5 as table has 5 columns
            numberOfGames++;
            listSize-=5;
        }
        GamesArray= new String[numberOfGames][5];// store elements of list in 2d array to make table
        int k=0;
        for (int i = 0; i < numberOfGames; i++) {
            for (int j = 0; j < 5; j++) {
                GamesArray[i][j] = gameDataList.get(k);
                k++;
            }
        }
    }


}
