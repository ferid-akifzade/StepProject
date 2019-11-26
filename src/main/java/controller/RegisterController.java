package controller;

import  libs.CommandException;
import  libs.Person;
import  service.RegisterService;

import java.io.IOException;

class RegisterController {
    Person run(String input) throws IOException, CommandException {
        RegisterParser parser = new RegisterParser(input);
        RegisterService service = new RegisterService(parser);
        return service.run();
    }
}
