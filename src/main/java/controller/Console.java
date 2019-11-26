package controller;

import java.util.Scanner;

public class Console {

    private Controller controller = new Controller();

    public void run()
    {
        while (!LogRegController.exit) {
            Scanner userInput = new Scanner(System.in);
            if(!LogRegController.isLogged) {
                System.out.println("Welcome. If you have account, please sign in. If you have not, please register." +
                        "\nFor exit please type [exit]. Command: [login]/[register]/[exit]");
            }
            else
            {
                Menu mainMenu = new Menu();
                System.out.println(mainMenu.getMainMenu());
            }
            String command = userInput.nextLine();
            System.out.println(controller.run(command));
        }
    }
}
