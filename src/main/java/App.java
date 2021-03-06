import GUI.GUI;
import other.Vec2D;
import simulation.Solver;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private int _substeps = 6;
    private int _fps = 60;
    private GUI gof;

    public App() {
        Solver solver = new Solver();
        solver.addObject(new Vec2D(150, 330), 10);
        solver.addObject(new Vec2D(150, 300), 10);
        solver.addObject(new Vec2D(150, 270), 10);
        solver.addObject(new Vec2D(600, 300), 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gof = new GUI(this, solver, _substeps, _fps);
        gof.initialize(this.getContentPane());
        this.setSize(820,840);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
    }





    public static void main(String[] args) {
        new App();
    }
}
