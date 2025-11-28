package user;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<String, User> users = new HashMap<>();

    public User register(String username) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User u = new User(username);
        users.put(username, u);
        return u;
    }

    public User login(String username) {
        return  users.get(username); //null if it does not exist
    }
}
