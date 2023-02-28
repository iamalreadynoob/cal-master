package visualize;

import arithmetic.ArithMain;
import basicTools.Parser;
import basicTools.Predefinitions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Buttons
{

    protected Buttons()
    {
        Screen.calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                calculationScheme(Screen.inputField.getText());
            }
        });

        Screen.seeGuide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

            }
        });

    }

    protected void calculationScheme(String output)
    {
        String[] parsed = new Parser(output).parse().toArray(new String[0]);

        for (int i = 0; i < parsed.length; i++)
        {

            if (new Predefinitions().isOperator(parsed[i]))
            {
                Screen.outputField.setText(new ArithMain(parsed).getAnswer());
                break;
            }

            else if (new Predefinitions().isTrig(parsed[i]))
            {

            }

            else if (new Predefinitions().isArithComm(parsed[i]))
            {

            }

            else if (new Predefinitions().isConstant(parsed[i]))
            {

            }

        }

    }

}
