package com.vaadin.flow.tutorial.binder.data;

import java.util.ArrayList;
import java.util.List;
import org.jfairy.Fairy;
import org.jfairy.producer.person.Person;

public class DataGenerator {

    private static final Fairy fairy = Fairy.create();
    private static final String COMMENT= "Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah";

    public static List<User> getUsers(){
        List<User> users= new ArrayList<>();
        for (int i = 0; i < 20; i++){
            Person person = fairy.person();
            users.add(new User(person.email(),person.firstName(),person.lastName(),COMMENT));
        }
        return  users;
    }
}
