package bean;

public class User {
    public int uid;
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int uid) {
        this(username, password);
        this.uid = uid;
    }
}
