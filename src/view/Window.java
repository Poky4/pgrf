package view;

/*
 *
 * @author KŠ - PGRF1 FIM UHK
 * @version 2024.c04.t02
 */

import javax.swing.*;

public class Window extends JFrame {

    private final Panel panel;

    public Window() {
        panel = new Panel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UHK FIM PGRF1 C04 " + this.getClass().getName());
        add(panel);
        setVisible(true);
        pack();

        panel.setFocusable(true);
        panel.grabFocus();
    }

    public Panel getPanel() {
        return panel;
    }
}
