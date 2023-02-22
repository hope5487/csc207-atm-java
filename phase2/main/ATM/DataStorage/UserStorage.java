package ATM.DataStorage;

import ATM.BankUsers.BankWorker;
import ATM.BankUsers.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserStorage {

    private final ArrayList<User> userList = new ArrayList<>();
    public ArrayList<User> getUserList() {
        return userList;
    }

    public void readUserFile(){
        try {
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir")
                    + "/phase2/inputTextFiles/user.txt"));
            while (scanner.hasNextLine()) {
                String[] record = scanner.nextLine().split(",");
                verifyUserOrWorker(record);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void verifyUserOrWorker(String[] record){
        User user;
        if (record[4].equals("user")){
            user = new User(record[0], record[1], Integer.parseInt(record[2]), Integer.parseInt(record[3]));
        } else {
            user = new BankWorker(record[0], record[1], Integer.parseInt(record[2]), Integer.parseInt(record[3]));
        }
        userList.add(user);
    }

    public void writeUserFile(){
        FileWriter fw;
        try {
            fw = new FileWriter(new File(System.getProperty("user.dir")
                    + "/phase2/inputTextFiles/user.txt"));
            for (User user : userList) {
                fw.write(user.getUserName() + "," + user.getPassword() + "," +
                        user.getPrimaryAccountNumber() + "," + user.getPortfolioNumber());
                if (user instanceof BankWorker){
                    fw.write(",worker");
                } else {
                    fw.write(",user");
                }
                fw.write(System.lineSeparator()); //new line
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public User searchUser(String userName){
        for (User user : userList){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }
}
