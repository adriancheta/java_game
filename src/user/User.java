package user;

public class User {

    private String username;
    //TODO: password, stats (ex: record, kills, assists, deaths);

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
