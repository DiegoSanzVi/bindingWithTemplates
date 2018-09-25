package demo;

import java.util.ArrayList;
import java.util.List;
import org.jfairy.Fairy;
import org.jfairy.producer.person.Person;

public class DataGenerator {

    private static final Fairy fairy = Fairy.create();
    private static final String COMMENT= "Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah";

    public static UserComment getUserComment(){
        return new UserComment("diego@gmail.com","Diego","Sanz",COMMENT);
    }

    public static List<UserComment> getUserComments(){
        List<UserComment> userComments = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            Person person = fairy.person();
            userComments.add(new UserComment(person.email(),person.firstName(),person.lastName(),COMMENT));
        }
        return  userComments;
    }
}
