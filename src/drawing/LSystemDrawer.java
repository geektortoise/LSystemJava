package drawing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LSystemDrawer {
    private float angle;

    private int step;

    private String sentence;

    public LSystemDrawer(float angle, int step, String sentence) {
        this.angle= angle;
        this.step = step;
        this.sentence = sentence;
    }
    public void draw(File output) throws IOException {
        double x = 0, y = 0;
        float currentAngle = 0f;
        List<PenPointer> coordinates = new ArrayList<>();
        Deque<PenPointer> stack = new ArrayDeque<>();
        for(char c : sentence.toCharArray()){
            if(c == '-') currentAngle-= angle;
            if(c == '+') currentAngle+= angle;
            if(c == 'F') {
                x += step*Math.cos(Math.toRadians(currentAngle));
                y += step*Math.sin(Math.toRadians(currentAngle));
                coordinates.add(new PenPointer(x, y, currentAngle));
            }
            if(c == '[') stack.push(new PenPointer(x, y, currentAngle));
            if(c == ']') { //pop de la stack;
                PenPointer pp = stack.pop();
                x = pp.x();
                y = pp.y();
                currentAngle = pp.angle();
            }
            if(c == '|')  currentAngle+= 180;
            //System.out.println("x : "+x+", y : "+y+", angle : "+currentAngle);
        }

        StringBuilder svg = new StringBuilder();
        svg.append("<svg id=\"svg\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"");

        int offset = 5;
        int minX = (int)(coordinates.stream().mapToDouble(c -> c.x()).min().getAsDouble());
        //System.out.println("minX ="+minX);
        minX = Math.min(minX, 0) -offset;
        //System.out.println("minX ="+minX);
        int minY = (int)(coordinates.stream().mapToDouble(c -> c.y()).min().getAsDouble());
        minY = Math.min(minY, 0) -offset;
        int maxX = (int)(coordinates.stream().mapToDouble(c -> c.x()).max().getAsDouble());
        //System.out.println("maxX ="+maxX);
        maxX = Math.max(maxX, 0) +offset;
        //System.out.println("maxX ="+maxX);
        int maxY = (int)(coordinates.stream().mapToDouble(c -> c.y()).max().getAsDouble());
        //System.out.println("maxY ="+maxY);
        maxY = Math.max(maxY, 0) +offset;
        //System.out.println("maxY ="+maxY);
        int min = Math.min(minX, minY);
        int max = Math.max(maxX, maxY);

        //System.out.println("min ="+min+", max ="+max);
        svg.append(minX+" "+minY+" "+(maxX - minX)+" "+(maxY - minY)+" ");

        svg.append("\">");
        svg.append("<path d=\"M0 0L");

        coordinates.stream().forEach(c -> svg.append(c.x()+" "+c.y()+" "));

        svg.append("\" fill=\"none\" stroke-width=\"1\" stroke=\"#008000\"></path>");
        svg.append("</svg>");

        try(var writer = new BufferedWriter(new FileWriter(output))) {
            writer.write(svg.toString());
        }

    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
