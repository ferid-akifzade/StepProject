package controller;

import  libs.CommandException;
import  libs.Log;
import  libs.Person;
import  libs.UserData;

import java.util.Objects;

public class RegisterParser {
    private Log log;

    public RegisterParser(RegisterParser parser) {
        this.log = new Log(parser.getLog());
    }

    RegisterParser(String command) throws CommandException {
        parse(command);
    }

    private void parse(String command) throws CommandException {
        boolean isUsername = false;
        boolean isPassword = false;
        boolean isName = false;
        boolean isSurname = false;
        short count = 0;
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder surname = new StringBuilder();
        for (char ch : command.toLowerCase().toCharArray()) {
            switch (count) {
                case 0:
                    isUsername = true;
                    isPassword = false;
                    isName = false;
                    isSurname = false;
                    break;
                case 1:
                    isUsername = false;
                    isPassword = true;
                    isName = false;
                    isSurname = false;
                    break;
                case 2:
                    isUsername = false;
                    isPassword = false;
                    isName = true;
                    isSurname = false;
                    break;
                case 3:
                    isUsername = false;
                    isPassword = false;
                    isName = false;
                    isSurname = true;
                    break;
            }
            if (ch == ',') {
                count++;
            } else if (ch != ' ') {
                if (isUsername) username.append(ch);
                if (isPassword) password.append(ch);
                if (isName) name.append(ch);
                if (isSurname) surname.append(ch);
            }
        }
        if (username.length() != 0 && password.length() != 0 && name.length() != 0 && surname.length() != 0)
            log = new Log(new Person(name.toString(), username.toString()), new UserData(username.toString(), password.toString()));
        else throw new CommandException("Command error. Please write all necessary information");
    }

    @Override
    public String toString() {
        return log.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterParser)) return false;
        RegisterParser that = (RegisterParser) o;
        return log.equals(that.log);
    }

    @Override
    public int hashCode() {
        return Objects.hash(log);
    }

    public Log getLog() {
        return log;
    }
}
