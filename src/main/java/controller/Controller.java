package controller;
import libs.Person;
import service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


class Controller {
    private Person person;

    ArrayList<String> run(String command) {
        try {
            if (!LogRegController.isLogged) {
                LogRegController logRegController = new LogRegController();
                person = logRegController.start(command);
                LogRegController.isLogged = true;
            }
            else {
                CommandParser commandParser = new CommandParser(command);
                Service service = new Service(commandParser);
                try {
                    return service.run(person);
                } catch (Exception e) {
                    return new ArrayList<>(Collections.singleton(e.getMessage()));
                }
            }
        } catch (IOException e) {
            return new ArrayList<>(Collections.singleton(e.getMessage()));
        }
        return new ArrayList<>(Collections.singletonList(""));
    }
}

