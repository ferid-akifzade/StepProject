package controller;

import java.util.ArrayList;

public class CommandParser {

    private ArrayList<StringBuilder> data;
    private StringBuilder commandSb;

    public CommandParser(CommandParser commandParser) {
        this.data = commandParser.getData();
        this.commandSb = commandParser.getCommand();
    }

    public CommandParser(String command) {
        parseCommand(command);
    }


    public boolean isCommandAvailable() {
        String availableCommands = "l,b,s,r,c,m,e";
        return availableCommands.contains(commandSb.toString());
    }

    private void parseCommand(String command) {
        data = new ArrayList<>();
        commandSb = new StringBuilder();
        StringBuilder oneData = new StringBuilder();

        boolean isCommand = false;
        boolean isAffected = false;
        for (char ch : command.toCharArray()) {
            switch (ch) {
                case '-':
                    isCommand = true;
                    isAffected = false;
                    break;
                case ' ':
                    if (isCommand) {
                        isCommand = false;
                        isAffected = true;
                    }
                    break;
                case ',':
                    if (oneData.toString().length() != 0 && data.size() < 2) {
                        data.add(new StringBuilder(oneData.toString().trim()));
                        oneData = new StringBuilder();
                    }
                    break;
                default:
                    if (isCommand) commandSb.append(ch);
                    else if (isAffected) oneData.append(ch);
            }
        }
        if (oneData.toString().length() != 0 && data.size() < 2)
            data.add(new StringBuilder(oneData.toString().trim()));

    }

    public ArrayList<StringBuilder> getData() {
        return data;
    }

    public StringBuilder getCommand() {
        return commandSb;
    }
}
