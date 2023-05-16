package org.example.service;

import org.example.model.Role;
import org.example.model.User;
import org.example.model.UserStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.example.model.Role.CUSTOMER;

public class AuthService {
    private final String filename = "data/user.csv";
    public void signup(User user) {
        List<Long> listId = new ArrayList<>();
        List<String> listUser = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            while ((line = br.readLine()) != null){
                listUser.add(line);
                String[] results = line.split(",");
                listId.add(Long.parseLong( results[0]));
            }
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            long id = listId.get(listId.size() - 1) + 1;
            user.setRole(CUSTOMER);
            user.setUserStatus(UserStatus.ALLOW);
//            long id, String name, String address, String phonenumber,String username ,String password, Role role, UserStatus
//            userStatus, long borrowbookid
            String res = id + "," + user.getName() +","+ user.getAddress() + "," +user.getPhonenumber() + "," + user.getPassword() + "," + user.getRole() + "," + user.getUserStatus() ;
            listUser.add(res);

            PrintWriter print = new PrintWriter(filename);
            for (Object item: listUser) {
                print.println(item);
            }
            print.flush();
            print.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean signin(String phonenumber, String password) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line = null;

            while ((line = br.readLine()) != null){
                String[] results = line.split(",");
                if (results[3].equals(phonenumber) && results[4].equals(password)){
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
