package generation;

import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

public class LSystemFactory {
    public static LSystemGeneration getLevyCurve(boolean simplified) {
        IntFunction<String> generator = (c -> {
            if (c != 'F') return ""+(char)c;
            else return "-F++F-";
        });
        UnaryOperator<String> simplifier = (s -> s.replace("+-", "").replace("-+", ""));
        var lSys = new LSystemGeneration(0,"F", generator);
        if(simplified) lSys.setSimplifier(simplifier);

        return lSys;
    }

    public static LSystemGeneration getRings(boolean simplified) {
        IntFunction<String> generator = (c -> {
            if (c != 'F') return ""+(char)c;
            else return "FF+F+F+F+F+F-F";
        });
        UnaryOperator<String> simplifier = (s -> s.replace("+-", "").replace("-+", ""));
        var lSys = new LSystemGeneration(0,"F+F+F+F", generator);
        if(simplified) lSys.setSimplifier(simplifier);

        return lSys;
    }

    public static LSystemGeneration getVanKochSnowflake(boolean simplified) {
        IntFunction<String> generator = (c -> {
            if (c != 'F') return ""+(char)c;
            else return "F-F++F-F";
        });
        UnaryOperator<String> simplifier = (s -> s.replace("+-", "").replace("-+", ""));
        var lSys = new LSystemGeneration(0,"F++F++F", generator);
        if(simplified) lSys.setSimplifier(simplifier);

        return lSys;
    }

    public static LSystemGeneration getPlant(boolean simplified) {
        IntFunction<String> generator = (c -> {
            if (c != 'F') return ""+(char)c;
            else return "FF+[+F-F-F]-[-F+F+F]";
        });
        UnaryOperator<String> simplifier = (s -> s.replace("+-", "").replace("-+", ""));
        var lSys = new LSystemGeneration(0,"----F", generator);
        if(simplified) lSys.setSimplifier(simplifier);

        return lSys;
    }

    public static LSystemGeneration getPentaplex(boolean simplified) {
        IntFunction<String> generator = (c -> {
            if (c != 'F') return ""+(char)c;
            else return "F++F++F|F-F++F";
        });
        UnaryOperator<String> simplifier = (s -> s.replace("+-", "").replace("-+", ""));
        var lSys = new LSystemGeneration(0,"F++F++F++F++F", generator);
        if(simplified) lSys.setSimplifier(simplifier);

        return lSys;
    }


}
