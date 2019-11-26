package controller;

import libs.CommandException;
import libs.Person;
import service.LoginService;

import java.io.IOException;

class LoginController {
    Person run(String input) throws CommandException, IOException {
        LoginParser parser = new LoginParser(input);
        LoginService service = new LoginService(parser);
        return service.run();
    }
}
