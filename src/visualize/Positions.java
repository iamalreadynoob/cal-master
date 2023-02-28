package visualize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Positions
{

    protected Positions(JFrame frame)
    {
        Font fieldFont = new Font("arial", Font.BOLD, 25);

        Screen.inputField.setBounds(20, 20, 410, 50);
        Screen.inputField.setBackground(Color.BLACK);
        Screen.inputField.setForeground(Color.RED.darker());
        Screen.inputField.setFont(fieldFont);
        Screen.inputField.setBorder(null);
        Screen.inputField.setText("type...");

        Screen.outputField.setBounds(20, 90, 460, 50);
        Screen.outputField.setBackground(Color.BLACK);
        Screen.outputField.setForeground(Color.RED.darker());
        Screen.outputField.setFont(fieldFont);
        Screen.outputField.setBorder(null);
        Screen.outputField.setEditable(false);

        Screen.calculate.setBounds(430, 20, 50, 50);
        Screen.calculate.setBackground(Color.MAGENTA.darker());
        Screen.calculate.setForeground(Color.WHITE);
        Screen.calculate.setBorder(null);
        Screen.calculate.setText("=>");
        Screen.calculate.setToolTipText("calculate");

        Screen.seeGuide.setBounds((frame.getWidth() / 2) - 50, 220, 100, 30);
        Screen.seeGuide.setBackground(Color.WHITE);
        Screen.seeGuide.setForeground(Color.BLACK);
        Screen.seeGuide.setBorder(null);
        Screen.seeGuide.setText("see guide");
        Screen.seeGuide.setToolTipText("open the browser...");


        Screen.inputField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);

                if (Screen.inputField.getText().equals("type...")) Screen.inputField.setText(null);
            }
        });

        Screen.inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    new Buttons().calculationScheme(Screen.inputField.getText());
                }

            }
        });

    }

}
