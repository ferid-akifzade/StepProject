package service;

import  controller.LoginParser;
import  dao.LogsDAO;
import  libs.CommandException;
import  libs.Log;
import  libs.Person;
import  libs.UserData;

import java.io.IOException;
import java.util.Optional;

public class LoginService {
    private LogsDAO logs;
    private LoginParser localParser;

    public LoginService(LoginParser parser) throws IOException {
        localParser = new LoginParser(parser);
        logs = new LogsDAO();
    }

    public Person run() throws CommandException {
        UserData userData = localParser.getUserData();
        Optional<UserData> loggedUser = getUserdataByUsername(userData.getUsername());
        if (loggedUser.isPresent() && userData.getPassword().equals(loggedUser.get().getPassword())) {
            Optional<Person> result = getPersonByUsername(userData.getUsername());
            if (result.isPresent()) return result.get();
        }
        throw new CommandException("Username or password error");
    }

    private Optional<Person> getPersonByUsername(String username) {
        for (Log log : logs) {
            if (log.getUserData().getUsername().toLowerCase().equals(username.toLowerCase()))
                return Optional.of(log.getPerson());
        }
        return Optional.empty();
    }

    private Optional<UserData> getUserdataByUsername(String username) {
        for (Log log : logs) {
            if (log.getUserData().getUsername().toLowerCase().equals(username.toLowerCase()))
                return Optional.of(log.getUserData());
        }
        return Optional.empty();
    }
}
