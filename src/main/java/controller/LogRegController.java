package controller;

import libs.CommandException;
import libs.Person;

import java.io.IOException;
import java.util.Scanner;

public class LogRegController {

    public static boolean exit = false;
    public static boolean isLogged = false;
    private String getUserInput(Scanner userInput) {
        return userInput.nextLine();
    }

    Person start(String userInput) throws IOException {
        boolean end = false;
        while (!end) {
            switch (userInput.toLowerCase()) {
                case "login":
                    try {
                        System.out.println("Please write username and password comma separated: \n[username],[password]\n[exit] for exiting");
                        LoginController controller = new LoginController();
                        String loginInput = getUserInput(new Scanner(System.in)).toLowerCase();
                        if(!loginInput.equals("exit"))
                        return controller.run(loginInput);
                        else userInput = "exit";
                    } catch (CommandException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "register":
                    try {
                        System.out.println("Please write username, password, name and surname comma separated: \n[username],[password],[name],[surname]\n[exit] for exiting");
                        String registerInput = getUserInput(new Scanner(System.in));
                        RegisterController controller = new RegisterController();
                        if(!registerInput.equals("exit"))
                            return controller.run(registerInput);
                        else userInput = "exit";
                    } catch (CommandException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    end = true;
                    LogRegController.exit = true;
                    break;
                default:
                    System.out.println("Command error. Please write only [login], [register] or [exit].");
            }
        }
        return null;
    }
}
