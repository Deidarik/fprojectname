package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeTest {

    @Test
    void firstAnalyze() {
        Analyze an = new Analyze("x+arcsin(x*45*sin(x)*x)");
        an.firstAnalyze();
        for(int i = 0; i<an.readyParts.size();i++)
        {
            System.out.println(an.readyParts.get(i).word + ' ' + an.readyParts.get(i).type);
        }
    }
}