package controller;

import libs.CommandException;
import libs.UserData;

public class LoginParser {
    private UserData userData;

    public LoginParser(LoginParser parser)
    {
        userData = parser.getUserData();
    }

    LoginParser(String userData) throws CommandException {
        parse(userData);
    }

    private void parse(String command) throws CommandException {
        short count = 0;
        boolean isUserName = true;
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        for (char ch : command.toLowerCase().toCharArray()) {
            if (ch == ',') {
                isUserName = false;
                count++;
            } else if (ch != ' '){
                if (isUserName) username.append(ch);
                else if (count < 2) password.append(ch);
            }
        }
        if (username.length() != 0 && password.length() != 0)
        userData = new UserData(username.toString(),password.toString());
        else throw new CommandException("Command error. Please write username and password in correct way");
    }

    public UserData getUserData() {
        return userData;
    }
}
