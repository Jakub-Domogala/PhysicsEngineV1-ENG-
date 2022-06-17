package simulation;

import other.Vec2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Solver extends JComponent implements MouseListener, ComponentListener {
    private int cicrleConstRadius = 380;
    private int moveCircleRightDown = 20;
    private Vec2D gravity;
    private final ArrayList<VerletObject> objects;

    private int iteration = 0;

    public Solver() {
        objects = new ArrayList<>();
        gravity = new Vec2D(0 ,2000);
    }

    public String showObjects() {
        String str = new String();
        for (VerletObject obj: objects) {
            str += obj.toString() + "\t";
        }
        return str;
    }

    public void update(double dt, int substeps) {
        for(int i = 0; i < substeps; i++) {
            applyGravity();
            solveCollisions();
            updatePositions(dt / substeps);
            applyConstraintCircular();
        }
        iteration++;


//        if (iteration < 1400 && iteration > 20 && iteration % 100 > 20) {
//            if (iteration % 2 == 0) {
//                this.addObject(new Vec2D(380, 100), 6, 0, iteration, new Vec2D(-2, 0));
//            }
//            if (iteration % 2 == 0) {
//                this.addObject(new Vec2D(420, 100), 6, 0, iteration+100, new Vec2D(2, 0));
//            }
//        }
//        if(iteration % 80 == 0) {
//            System.out.println(objects.size());
//        }

        this.repaint();
    }



    private void updatePositions(double dt) {
        for (VerletObject obj: objects) {
            obj.updatePosition(dt);
        }
    }

    private void applyGravity() {
        for (VerletObject obj: objects) {
            obj.accelerate(gravity);
        }
    }

    private void applyConstraintCircular() {
        Vec2D circlePosition = new Vec2D(cicrleConstRadius + moveCircleRightDown, cicrleConstRadius + moveCircleRightDown);
        int radius = cicrleConstRadius;
        for (VerletObject obj: objects) {
            Vec2D to_obj = obj.getPosition().subtract(circlePosition);
            double distance = to_obj.abs();
            if (distance > radius - obj.getRadius()) {
                Vec2D normal = to_obj.divide(distance);
                obj.setCurrentPsition(circlePosition.add(normal.multiply(radius - obj.getRadius())));
            }
        }
    }

    private void solveCollisions() {
        for (VerletObject obj1: objects) {
            for (VerletObject obj2: objects) {
                if (!obj1.equals(obj2)) {
                    Vec2D collisionAxis = obj1.getPosition().subtract(obj2.getPosition());
                    double dist = collisionAxis.abs();
                    if (dist < obj1.getRadius() + obj2.getRadius()) {
                        Vec2D normal = collisionAxis.divide(dist);
                        double delta = obj1.getRadius() + obj2.getRadius() - dist;
                        double weight1 = Math.pow(obj1.getRadius(), 2) / (Math.pow(obj1.getRadius(), 2) + Math.pow(obj2.getRadius(), 2));
                        double weight2 = (double) 1 - weight1;
                        obj1.setCurrentPsition(obj1.getPosition().add(normal.multiply(weight2 * delta)));
                        obj2.setCurrentPsition(obj2.getPosition().subtract(normal.multiply(weight1 * delta)));
                    }
                }
            }
        }
    }

    public void addObject(Vec2D position, int radius, double temperature, int ind, Vec2D velocity) {
        objects.add(new VerletObject(position, radius, temperature, ind, velocity));
    }

    public void addObject(Vec2D position, int radius) {
        objects.add(new VerletObject(position, radius));
    }

    public void addObject(Vec2D position) {
        objects.add(new VerletObject(position));
    }

    private void drawObjects(Graphics g) {
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(new Color(50, 50, 50, 255));
        int corn = 0;
        g.fillOval(moveCircleRightDown+corn, moveCircleRightDown+corn, cicrleConstRadius*2-corn, cicrleConstRadius*2-corn);
        for(VerletObject obj: objects) {
            Vec2D position = obj.getPosition();
//            System.out.println(position.toString());
            int x = (int)position.getX();
            int y = (int)position.getY();
            g.setColor(obj.getColor());
//            g.fillOval(x-obj.getRadius()/2, y-obj.getRadius()/2, obj.getRadius(), obj.getRadius());
            g.fillOval(x-obj.getRadius(), y-obj.getRadius(), obj.getRadius()*2, obj.getRadius()*2);
//            System.out.println("aaaa");

        }
    }

    protected void paintComponent(Graphics g) {
        drawObjects(g);
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
