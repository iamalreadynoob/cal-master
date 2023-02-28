package arithmetic;

import basicTools.Predefinitions;

import java.util.ArrayList;

public class CompareArith
{

    private String answer = null;
    private int amountSections = 0;
    private ArrayList<Integer> criticalPoints = new ArrayList<>();
    private ArrayList<String> signs = new ArrayList<>();
    private ArrayList<Double> actualValues = new ArrayList<>();

    protected CompareArith(String[] equation)
    {
        observe(equation);
        actualer(equation);
        answer = Boolean.toString(logicmaker(equation));
    }

    protected String getAnswer() {return answer;}

    private void observe(String[] equation)
    {
        if (equation[0] != "<" && equation[0] != ">" && equation[0] != "=" && equation[0] != "<="
        && equation[0] != ">=" && equation[equation.length-1] != "<" && equation[equation.length-1] != ">"
        && equation[equation.length-1] != "=" && equation[equation.length-1] != "<=" &&
        equation[equation.length-1] != ">=")
        {
            for (int i = 1; i < equation.length; i++)
            {
                if (equation[i].equals("<") || equation[i].equals(">") || equation[i].equals("=")
                || equation[i].equals("<=") || equation[i].equals(">="))
                {
                    amountSections++;
                    criticalPoints.add(i);
                }
            }
        }
    }

    private void actualer(String[] equation)
    {
        ArrayList<ArrayList<String>> rawValues = new ArrayList<>();

        for (int i = 0; i <= amountSections; i++)
        {
            ArrayList<String> temp = new ArrayList<>();
            int starter, finisher;

            if (i == 0) starter = 0;
            else starter = criticalPoints.get(i-1) + 1;

            if (i+1 > amountSections) finisher = equation.length;
            else finisher = criticalPoints.get(i);

            for (int j = starter; j < finisher; j++) temp.add(equation[j]);

            rawValues.add(temp);
        }

        for (int i = 0; i < rawValues.size(); i++)
        {
            String[] tempA = new String[rawValues.get(i).size()];
            for (int j = 0; j < tempA.length; j++) tempA[j] = rawValues.get(i).get(j);

            if (!isSimple(tempA))
            {
                double temp = 0;

                try
                {
                    temp = Double.parseDouble(tempA[0]);
                }
                catch (Exception e)
                {
                    if (new Predefinitions().isConstant(tempA[0]))
                    {
                        temp = new Predefinitions().getConstants().get(tempA[0]);
                    }
                }
                finally
                {
                    actualValues.add(temp);
                }
            }
            else
            {
                String[] temp = new String[rawValues.get(i).size()];
                for (int j = 0; j < temp.length; j++) temp[j] = rawValues.get(i).get(j);

                actualValues.add(Double.parseDouble(new BasicArith(temp).answer));
            }
        }
    }

    private boolean logicmaker(String[] equation)
    {
        Boolean boolans = null;

        for (int i = 0; i < amountSections; i++)
        {
            if (boolans == null)
            {
                boolans = logicPlugger(actualValues.get(i), actualValues.get(i+1), equation[criticalPoints.get(i)]);
            }

            else
            {
                boolans = boolans && logicPlugger(actualValues.get(i), actualValues.get(i+1), equation[criticalPoints.get(i)]);
            }

            if (!boolans) break;
        }

        return boolans;
    }

    private boolean isSimple(String[] equation)
    {
        boolean isSimple = true;

        for (int i = 0; i < equation.length; i++)
        {
            if (equation[i].equals("&&") || equation[i].equals("=")
                    || equation[i].equals("<") || equation[i].equals(">") || equation[i].equals("<=")
                    || equation[i].equals(">=") || equation[i].equals("||") || equation[i].equals("?"))
            {
                isSimple = false;
                break;
            }
        }

        return isSimple;
    }

    private boolean logicPlugger(double first, double second, String sign)
    {
        Boolean boolans = null;

        if (sign.equals("<"))
        {
            if (first < second) boolans = true;
            else boolans = false;
        }

        else if (sign.equals(">"))
        {
            if (first > second) boolans = true;
            else boolans = false;
        }

        else if (sign.equals("="))
        {
            if (first == second) boolans = true;
            else boolans = false;
        }

        else if (sign.equals("<="))
        {
            if (first <= second) boolans = true;
            else boolans = false;
        }

        else if (sign.equals(">="))
        {
            if (first >= second) boolans = true;
            else boolans = false;
        }

        return boolans;
    }

}
