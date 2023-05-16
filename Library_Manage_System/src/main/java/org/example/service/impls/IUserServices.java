package org.example.service.impls;

import org.example.model.Book;
import org.example.model.User;

import java.util.List;

public interface IUserServices {
    List<User> getListUser();
    User getUserDetail(long userid);
    User getUserDetailByPhoneNumber(String phonenumber);
    void add(User newuser);
    void delete(long userid);
    void edit(long userid, User updateuser);
}
