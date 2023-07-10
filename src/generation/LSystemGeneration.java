package generation;

import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class LSystemGeneration {
    private int iterationStepNumber;
    private String axiom;

    private IntFunction<String> generator;

    private UnaryOperator<String> simplifier;

    public LSystemGeneration(int steps, String axiom, IntFunction<String> generator) {
        this(steps, axiom, generator, null);
    }
    public LSystemGeneration(int steps, String axiom, IntFunction<String> generator, UnaryOperator<String> simplifier) {
        this.iterationStepNumber = steps;
        this.axiom = axiom;
        this.generator = generator;
        this.simplifier = simplifier;
    }

    public String generate() {
        String sentence = axiom;
        for(int i=0;i<iterationStepNumber;i++) {
            sentence = sentence.chars().mapToObj(generator).collect(Collectors.joining());
            if(simplifier != null) sentence = simplifier.apply(sentence);
        }
        return sentence;
    }

    public void setIterationStepNumber(int iterationStepNumber) {
        this.iterationStepNumber = iterationStepNumber;
    }

    public void setSimplifier(UnaryOperator<String> simplifier) {
        this.simplifier = simplifier;
    }
}
