package simulation;

import other.Vec2D;

import java.awt.*;

public class VerletObject {
//    Attributes
    private Color color;
    private Vec2D currentPsition;
    private Vec2D oldPsition;
    private final int radius;
    private double temperature;

    private Vec2D acceleration;

//    Constructors
    public VerletObject(Vec2D position, int radius, double temperature, int ind, Vec2D velocity) {
        this.currentPsition = position;
        this.oldPsition = position.subtract(velocity);
        this.radius = radius;
        this.temperature = temperature;
        this.acceleration = new Vec2D(0, 0);
        this.color = calcColor(ind);
    }
    private int rgbRainbowFunc(double a) {
        if((int)(a % 3) == 0) {
            return (int)((a - Math.floor(a)) * 255);
        }
        else if ((int)(a % 3) == 1) {
            return (int)((1 - (a - Math.floor(a))) * 255);
        }
        else {
            return 0;
        }
    }
    private Color calcColor(int ind) {
//        double val = (double)ind/100;
//        int offset = 2;
//        int r = (int)((Math.sin(val) + 1) * 120);
//        int g = (int)((Math.sin(val + offset) + 1) * 120);
//        int b = (int)((Math.sin(val + 2 * offset) + 1) * 120);
//        return new Color(r, g, b, 255);

        double val = (double)ind/60;
        int r = rgbRainbowFunc(val);
        int g = rgbRainbowFunc(val + 1);
        int b = rgbRainbowFunc(val + 2);
        return new Color(r, g, b, 255);
    }

    public VerletObject(Vec2D position, int radius) {
        this(position, radius, 0, 0, new Vec2D());
    }

    public VerletObject(Vec2D position) {
        this(position, 20, 0, 0, new Vec2D());
    }

//    Getters
    public String toString() {
        return currentPsition.toString();
    }

    public Vec2D getPosition() {
        return new Vec2D(currentPsition);
    }

    public int getRadius() {
        return radius;
    }

    public double getTemperature() {
        return temperature;
    }

    public Color getColor() {
        return color;
    }

//    Setters
    public void setCurrentPsition(Vec2D position) {
        currentPsition = position;
    }

//    Functionals
    public void updatePosition(double dt) {
        Vec2D velocity = currentPsition.subtract(oldPsition);
        oldPsition = currentPsition;
        currentPsition = currentPsition.add(velocity).add(acceleration.multiply(dt * dt));
        acceleration = new Vec2D(0, 0);
    }

    public void accelerate(Vec2D acceleration) {
        this.acceleration = this.acceleration.add(acceleration);
    }

}
