import drawing.LSystemDrawer;
import generation.LSystemFactory;
import generation.LSystemGeneration;

import java.io.File;
import java.io.IOException;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) throws IOException {
        //testLevyCurve();
        //testRings();
        //testSnowflakes();
        //testPlant();
        testPentaPlex();
    }

    private static void test(LSystemGeneration lSys, LSystemDrawer drawer, UnaryOperator<String> simplifier,
                             String name, int limit) throws IOException {
        for(int i=1;i<=limit;i++) {
            lSys.setIterationStepNumber(i);
            if(simplifier != null) {
                lSys.setSimplifier(simplifier);
            }

            String result = lSys.generate();
            System.out.println("result length is "+result.length());
            drawer.setSentence(result);
            drawer.draw(new File("lSys-"+name+i+".svg"));
        }
    }

    private static void testLevyCurve() throws IOException {
        var lSys = LSystemFactory.getLevyCurve(true);
        var drawer = new LSystemDrawer(45.0f, 10, "");
        test(lSys,
                drawer,
                (s -> s.replace("++++++++","").replace("--------","").replace("+-", "").replace("-+", "")),
                "levy-curve", 18);
    }
    private static void testRings() throws IOException {
        var lSys = LSystemFactory.getRings(true);
        var drawer = new LSystemDrawer(90.0f, 10, "");
        test(lSys,
                drawer,
                (s -> s.replace("++++","").replace("----","").replace("+-", "").replace("-+", "")),
                "rings", 5);
    }

    private static void testSnowflakes() throws IOException {
        var lSys = LSystemFactory.getVanKochSnowflake(true);
        var drawer = new LSystemDrawer(60.0f, 10, "");
        test(lSys,
                drawer,
                (s -> s.replace("++++++","").replace("------","").replace("+-", "").replace("-+", "")),
                "snow", 5);
    }

    private static void testPlant() throws IOException {
        var lSys = LSystemFactory.getPlant(true);
        var drawer = new LSystemDrawer(22.5f, 10, "");
        test(lSys,
                drawer,
                (s -> s.replace("+-", "").replace("-+", "")),
                "plant", 6);
    }

    private static void testPentaPlex() throws IOException {
        var lSys = LSystemFactory.getPentaplex(true);
        var drawer = new LSystemDrawer(36f, 10, "");
        test(lSys,
                drawer,
                (s -> s.replace("+-", "").replace("-+", "")),
                "penta", 7);
    }
}