package demo;

import java.util.List;

public class UsersCommentsRepository {

    private static List<UserComment> userComments;

    static {
        userComments = DataGenerator.getUserComments();
    }

    public static List<UserComment> getUserComments(){
        return userComments;
    }

    public static UserComment save(UserComment userComment){
        System.out.print(userComment);
        int index = userComments.indexOf(userComment);
        if ( index >= 0){
            userComments.set(index,userComment);
        }else{
            userComments.add(userComment);
        }
        return userComment;
    }

    public static void delete(UserComment userComment){
        userComments.remove(userComment);
    }
}
