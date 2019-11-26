package libs;

import java.util.Objects;

public class Log {
    private Person person;
    private UserData userData;

    public Log(Person person, UserData userData) {
        this.person = person;
        this.userData = userData;
    }

    public Log(Log log) {
        this.person = log.getPerson();
        this.userData = log.getUserData();
    }

    @Override
    public String toString() {
        return String.format("#%s:%s+%s-%s", userData.getUsername(), userData.getPassword(), person.getName(), person.getSurname());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return person.equals(log.person) &&
                userData.equals(log.userData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, userData);
    }

    public Person getPerson() {
        return person;
    }

    public UserData getUserData() {
        return userData;
    }
}
