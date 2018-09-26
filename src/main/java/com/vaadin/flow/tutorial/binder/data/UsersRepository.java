package com.vaadin.flow.tutorial.binder.data;

import java.util.List;
import java.util.Vector;

public class UsersRepository {

    private static List<User> userComments;

    static {
        // Vector is a thread safe version of ArrayList
        userComments = new Vector<>(DataGenerator.getUserComments());
    }

    public static List<User> getUserComments(){
        return userComments;
    }

    public static User save(User userComment){
        System.out.print(userComment);
        int index = userComments.indexOf(userComment);
        if ( index >= 0){
            userComments.set(index,userComment);
        }else{
            userComments.add(userComment);
        }
        return userComment;
    }

    public static void delete(User userComment){
        userComments.remove(userComment);
    }
}
