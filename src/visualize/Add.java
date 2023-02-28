package visualize;

import javax.swing.*;

public class Add
{

    protected Add(JFrame frame)
    {
        frame.add(Screen.inputField);
        frame.add(Screen.outputField);
        frame.add(Screen.calculate);
        frame.add(Screen.seeGuide);
    }

}
