package datagenerator;

import java.util.Random;

class FreeSitsGenerator {
    private Integer freeSits = 0;

    void generate() {
        Random sitGenerator = new Random();
        freeSits = Math.abs(sitGenerator.nextInt()) % 20+1;
    }

    Integer getFreeSits() {
        return freeSits;
    }
}
