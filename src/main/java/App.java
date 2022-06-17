import GUI.GUI;
import other.Vec2D;
import simulation.Solver;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private GUI gof;

    public App() {
        Solver solver = new Solver();
//        solver.addObject(new Vec2D(450, 300), 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gof = new GUI(this, solver);
        gof.initialize(this.getContentPane());
        this.setSize(800,800);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
    }





    public static void main(String[] args) {
        new App();
    }
}
