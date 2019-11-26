package dao;

import libs.Log;
import libs.Person;
import libs.UserData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class LogsDAO implements DAO<Log>, Iterable<Log> {
    private ArrayList<Log> database;

    public ArrayList<Log> getDatabase() {
        return database;
    }

    public LogsDAO() throws IOException {
        database = new ArrayList<>();
        read();
    }

    public void add(Log log) {
        database.add(log);
    }

    @Override
    public void read() throws IOException {
        ArrayList<Log> databaseLog = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("log.txt")));

        boolean isUsername = false;
        boolean isPassword = false;
        boolean isName = false;
        boolean isSurname = false;
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder surname = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            for (char ch : line.toCharArray()) {
                switch (ch) {
                    case '#':
                        isUsername = true;
                        isPassword = false;
                        isName = false;
                        isSurname = false;
                        break;
                    case ':':
                        isUsername = false;
                        isPassword = true;
                        isName = false;
                        isSurname = false;
                        break;
                    case '+':
                        isUsername = false;
                        isPassword = false;
                        isName = true;
                        isSurname = false;
                        break;
                    case '-':
                        isUsername = false;
                        isPassword = false;
                        isName = false;
                        isSurname = true;
                        break;
                    case ',':
                        Person person = new Person(name.toString(), surname.toString());
                        UserData userData = new UserData(username.toString(), password.toString());
                        databaseLog.add(new Log(person, userData));
                        username = new StringBuilder();
                        password = new StringBuilder();
                        name = new StringBuilder();
                        surname = new StringBuilder();
                        break;
                    default:
                        if (isUsername) username.append(ch);
                        if (isPassword) password.append(ch);
                        if (isName) name.append(ch);
                        if (isSurname) surname.append(ch);
                }

            }
        }
        database.addAll(databaseLog);
    }

    @Override
    public Iterator<Log> iterator() {
        return database.iterator();
    }
}
