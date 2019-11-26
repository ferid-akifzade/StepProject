package datagenerator;

public class LineGenerator {
    private String line;

    void generate(int lineCount) {
        StringBuilder sb = new StringBuilder();
        while (lineCount-- != 0) {
            FlightGenerator generator = new FlightGenerator();
            generator.generate();
            sb.append(generator.getFlights()).append(",");
        }
        line = sb.toString();
    }

    @Override
    public String toString() {
        return line + "\n";
    }
}
