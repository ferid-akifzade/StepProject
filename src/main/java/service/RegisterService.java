package service;

import  controller.RegisterParser;
import  dao.LogsDAO;
import  libs.CommandException;
import  libs.Log;
import  libs.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterService {
    private LogsDAO logs;
    private RegisterParser localParser;

    public RegisterService(RegisterParser parser) throws IOException {
        localParser = new RegisterParser(parser);
        logs = new LogsDAO();
    }

    public Person run() throws IOException, CommandException {
        Log data = getLog();
        if (!isRegistered(data))
        {
            add(data);
            write();
            return data.getPerson();
        }
        throw new CommandException("This username already used");
    }

    private void add(Log data) {
        logs.add(data);
    }

    private String databaseToWrite(LogsDAO data) {
        StringBuilder toWrite = new StringBuilder();
        for (Log oneLog : data) {
            toWrite.append(oneLog.toString()).append(",").append("\n");
        }
        return toWrite.toString();
    }

    private void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("log.txt")));
        writer.write(databaseToWrite(logs));
        writer.close();
    }

    private Log getLog() {
        return new Log(localParser.getLog());
    }

    private boolean isRegistered(Log data) {
        for (Log log : logs) {
            if (log.equals(data)) return true;
        }
        return false;
    }
}
