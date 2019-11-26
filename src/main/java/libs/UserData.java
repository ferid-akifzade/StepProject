package libs;

import java.util.Objects;

public class UserData {
    private String username;
    private String password;

    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;
        UserData userData = (UserData) o;
        return username.equals(userData.username) &&
                password.equals(userData.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
