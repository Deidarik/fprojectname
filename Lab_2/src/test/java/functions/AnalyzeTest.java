package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeTest {

    @Test
    void firstAnalyze() {
        Analyze an = new Analyze("arcsin x + arccos x");
        an.firstAnalyze();
        for(int i = 0; i<an.readyParts.size();i++)
        {
            System.out.println(an.readyParts.get(i).word + ' ' + an.readyParts.get(i).type);
        }
    }
}