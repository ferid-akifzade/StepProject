package datagenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookingGenerator {

    private BoardGenerator board = new BoardGenerator();

    public void generate() throws IOException {
        BufferedWriter databaseWrites = new BufferedWriter(new FileWriter(new File("database.txt")));
        commonGenerator();
        databaseWrites.write(board.getBoard());
        databaseWrites.close();
    }

    private void commonGenerator() {
        board.generate(7);
    }

}
