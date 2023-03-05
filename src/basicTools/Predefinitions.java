package basicTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Predefinitions
{

    private final ArrayList<String> operators, trig, arithComm;
    private final HashMap<String, Double> constants;

    public Predefinitions()
    {
        operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "(", ")", "&&", "%", "#", "^", "=", "==", "<", ">", "<=", ">=", "||", "?", "!"));
        trig = new ArrayList<>(Arrays.asList("sin", "cos", "tan", "cot", "sec", "csc", "arcsin", "arccos", "arctan", "arccot", "arcsec", "arccsc"));
        arithComm = new ArrayList<>(Arrays.asList("sum", "find"));

        constants = new HashMap<>();
        constants.put("pi", 3.141592);
        constants.put("e", 2.7182818284);
        constants.put("golden", 1.6180339);
        constants.put("g", 9.807);
        constants.put("feigenbaum", 4.669201);
        constants.put("khinchin", 2.685452);
    }

    public boolean isOperator(String word)
    {
        boolean isIt = false;

        for (int i = 0; i < operators.size(); i++)
        {
            if (word.equals(operators.get(i)))
            {
                isIt = true;
                break;
            }
        }

        if (!isIt)
        {
            int amount = 0;

            for (int i = 0; i < word.length(); i++)
            {
                if (word.charAt(i) == '!' || word.charAt(i) == '^' || word.charAt(i) == '#')
                {
                    amount++;
                    isIt = true;
                }

                if (amount > 1)
                {
                    isIt = false;
                    break;
                }
            }
        }

        return isIt;
    }

    public boolean isTrig(String word)
    {
        boolean isIt = false;

        for (int i = 0; i < trig.size(); i++)
        {
            if (word.equals(trig.get(i)))
            {
                isIt = true;
                break;
            }
        }

        return isIt;
    }

    public boolean isConstant(String word)
    {
        boolean isIt = false;

        Double test = constants.get(word);

        if (test != null) isIt = true;

        return isIt;
    }

    public boolean isArithComm(String word)
    {
        boolean isIt = false;

        for (int i = 0; i < arithComm.size(); i++)
        {
            if (word.equals(arithComm.get(i)))
            {
                isIt = true;
                break;
            }
        }

        return isIt;
    }

    public HashMap<String, Double> getConstants() {return constants;}

}
