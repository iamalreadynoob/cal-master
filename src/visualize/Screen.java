package visualize;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame
{

    protected static JTextField inputField, outputField;
    protected static JButton calculate, seeGuide;

    public Screen()
    {
        this.setSize(500, 300);
        this.setResizable(false);
        this.setBackground(Color.GRAY.darker());
        this.setLayout(null);

        new CreateNew();
        new Add(this);
        new Positions(this);
        new Buttons();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
