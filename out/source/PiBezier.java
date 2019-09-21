import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.pdf.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PiBezier extends PApplet {



float radius;
int totalChars = 1000000;
String pi;

public void settings() {
    size(800,800, PDF, "million(1).pdf");
}

public void setup() {
    colorMode(HSB, 1);
    radius = width*.4f;
    pi = loadStrings("pi-million.txt")[0];
}

public void draw() {
    background(0);
    stroke(0, 0, 1);
    fill(0, 0, 0);
    pushMatrix();
    translate(width/2, height/2);
    ellipse(0, 0, radius*2, radius*2);
    for(int n = 0; n < 10; n++) {
        float x = cos((float)(n*(Math.PI/5)))*(radius+10);
        float y = sin((float)(n*(Math.PI/5)))*(radius+10);
        fill(0, 0, 1);
        text(n, x, y);
    }

    fill(0, 0, 1, 0);
    for(int i = 0; i < totalChars; i++) {
        if(i == totalChars-1) {
            continue;
        }
        float rDist = random(-radius/2, radius/2);
        //println(rDist/radius);

        //float rAng = random((float)-(Math.PI/6)/2, (float)(Math.PI/6)/2);
        float offset = (float)((Math.PI*i)/(6*totalChars));
        float cRadius = (radius-5)-(((radius-5)*i)/totalChars);
        println(cRadius);

        int digit1 = pi.charAt(i) - '0';
        float angle1 = (float)(digit1*(Math.PI/5)) + offset;
        PVector point1 = new PVector(radius*cos(angle1), radius*sin(angle1));
        PVector cp1 = new PVector(cRadius*cos(angle1), cRadius*sin(angle1));

        int digit2 = pi.charAt(i+1) - '0';
        float angle2 = (float)(digit2*(Math.PI/5)) + offset;
        PVector point2 = new PVector(radius*cos(angle2), radius*sin(angle2));
        PVector cp2 = new PVector(cRadius*cos(angle2), cRadius*sin(angle2));

        stroke(digit2/10.0f, 1, 1, .0008f);
        bezier(point1.x, point1.y, cp1.x, cp1.y, cp2.x, cp2.y, point2.x, point2.y);
    }
    popMatrix();
    println("Finished.");
    exit();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PiBezier" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
