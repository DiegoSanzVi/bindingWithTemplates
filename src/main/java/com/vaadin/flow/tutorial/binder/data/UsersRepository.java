package com.vaadin.flow.tutorial.binder.data;

import java.util.List;
import java.util.Vector;

public class UsersRepository {

    private static List<User> users;

    static {
        // Vector is a thread safe version of ArrayList
        users = new Vector<>(DataGenerator.getUsers());
    }

    public static List<User> getUsers(){
        return users;
    }

    public static User save(User user){
        System.out.print(user);
        int index = users.indexOf(user);
        if ( index >= 0){
            users.set(index,user);
        }else{
            users.add(user);
        }
        return user;
    }

    public static void delete(User user){
        users.remove(user);
    }
}
