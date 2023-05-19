package org.example.service;

import org.example.model.*;
import org.example.service.impls.IUserServices;
import org.example.utils.CSVUltis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UserServices  {
    List<User> list = new ArrayList<>();
    List<Borrowbook> borrowbookList = new ArrayList<>();
    BorrowBookService borrowBookService = new BorrowBookService();
    String filename = "data/user.csv";


//    @Override
    public List<User> getListUser() {
        List<User> newUserList = new ArrayList<>();
        List<String> reads = CSVUltis.read(filename);
        Book book = new Book();

        for (String read : reads) {
            String[] user = read.split(",");

            newUserList.add(new User(
                    Integer.parseInt(user[0]),
                    user[1],
                    user[2],
                    user[3],
                    user[4],
                    Role.valueOf(user[5]),
                    UserStatus.valueOf(user[6])
            ));
//            long id, String name, String address, String phonenumber, String password, Role role, UserStatus
//            userStatus, List< Book > borrowbook
        }
        return newUserList;

    }

//    @Override
    public User getUserDetail(long userid) {
        list = getListUser();
        for (User user : list) {
            if (user.getId() == userid) {
                return user;
            }
        }
        return null;
    }

//    @Override
    public User getUserDetailByPhoneNumber(String phonenumber) {
        list = getListUser();
        for (User user : list) {
            if (user.getPhonenumber().equals(phonenumber)) {
                return user;
            }
        }
        return null;
    }

//    @Override
    public void add(User newuser) {
        list = getListUser();
        list.add(newuser);

        CSVUltis.write(filename,list);

    }

    public void checkNumber(int number) {
        try {
            int number1 = number;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void delete(long userid) {
//        list = getListUser();
//        User user = getUserDetail(userid);
//        list.remove(user);
//        CSVUltis.write(filename,list);
//
//    }
//
//    @Override
//    public void edit(long userid, User updateuser) {
//        list = getListUser();
//        for (User user: list){
//            if (user.getId() == userid){
//                user.setName(updateuser.getName());
//                user.setPassword(updateuser.getPassword());
//                user.setRole(updateuser.getRole());
//
//            }
//        }
//        CSVUltis.write(filename,list);
//
//    }
    public void handleExpiredAccount(User user){
        borrowbookList = borrowBookService.displayBorrowBook(user);
        for (Borrowbook borrowbook : borrowbookList){
            if(borrowbook.getExpDate().isBefore(LocalDate.now())){
                user.setUserStatus(UserStatus.DENY);
                break;
            } else {
                user.setUserStatus(UserStatus.ALLOW);
            }
        }
        list = getListUser();
        for(User user1: list){
            if (user1.getId() == user.getId()){
                user1.setUserStatus(user.getUserStatus());
            }
        }
        CSVUltis.write(filename,list);
    }
    public void getMessage(User user, String message){
        borrowbookList = borrowBookService.displayBorrowBook(user);
        for (Borrowbook borrowbook : borrowbookList){
            if (borrowbook.getExpDate().minusDays(1).equals(LocalDate.now()) || borrowbook.getExpDate().equals(LocalDate.now())){
                System.out.println(message);
                System.out.println();
                break;
            }
        }
    }
}

