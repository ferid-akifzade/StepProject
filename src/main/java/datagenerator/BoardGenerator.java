package datagenerator;

public class BoardGenerator {

    private String board = "";

    void generate(int size) {
        StringBuilder sb = new StringBuilder();
        while (size-- != 0) {
            LineGenerator line = new LineGenerator();
            line.generate(10);
            sb.append(line.toString());
        }
        board = sb.toString();
    }

    static Integer id = 0;

    String getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return board + "\n";
    }
}
