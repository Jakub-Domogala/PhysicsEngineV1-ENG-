package GUI;

import simulation.Solver;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener, ChangeListener {
    private Timer timer;
    public Solver solver;
    private int iterNum = 0;
    private boolean run = true;
    private int initDelay = 5;
    private int substeps = 4;
    private JFrame frame;

    public GUI(JFrame jf, Solver solver) {
        this.frame = jf;
        this.solver = solver;
        timer = new Timer(initDelay, this);
        timer.start();
    }


    public void initialize(Container container) {
        container.setLayout(new BorderLayout());
        container.setSize(new Dimension(1000, 1000));
        container.add(solver, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(e.getSource().equals(timer)) {
            iterNum++;
            frame.setTitle(Integer.toString(iterNum));
            solver.update((double) 0.001 * initDelay, substeps);
//            System.out.println("a");
        }
    }

    public void stateChanged(ChangeEvent e) {
        timer.setDelay(initDelay);
    }
}
