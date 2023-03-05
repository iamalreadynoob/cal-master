package visualize;

import arithmetic.ArithMain;
import arithmetic.Trigonometry;
import basicTools.Parser;
import basicTools.Predefinitions;
import command.TerminalMain;

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

         if (new Predefinitions().isArithComm(parsed[0]))
        {
            Screen.outputField.setText(new TerminalMain(parsed).getAnswer());
        }

         else
         {
             boolean operated = false;
             for (int i = 0; i < parsed.length; i++)
             {
                 if (new Predefinitions().isOperator(parsed[i])) {
                     Screen.outputField.setText(new ArithMain(parsed).getAnswer());
                     operated = true;
                     break;
                 }
             }

             if (!operated)
             {

                 if (new Predefinitions().isTrig(parsed[0]))
                 {
                     double answer = new Trigonometry(parsed[0], Double.parseDouble(parsed[1])).getResult();

                     if (answer - Math.round(answer) < 0.0000001) Screen.outputField.setText(Integer.toString((int)Math.round(answer)));
                     else Screen.outputField.setText(Double.toString(answer));
                 }

                 else if (new Predefinitions().isConstant(parsed[0]))
                 {
                     Screen.outputField.setText(Double.toString(new Predefinitions().getConstants().get(parsed[0])));
                 }
             }
         }

    }

}
