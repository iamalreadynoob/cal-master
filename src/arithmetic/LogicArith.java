package arithmetic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LogicArith
{

    private String answer;
    private String[] equation;
    private int amountPoints = 0;
    private ArrayList<Integer> criticalPoints = new ArrayList<>();
    private ArrayList<Boolean> finalValues = new ArrayList<>();
    private ArrayList<ArrayList<String>> rawValues = new ArrayList<>();

    protected LogicArith(String[] equation)
    {
        this.equation = equation;

        observe();
        collect();
        actualizer();
        answer = Boolean.toString(finalResult());
    }

    protected String getAnswer() {return answer;}

    private void observe()
    {
        if (equation[0].equals("&&") || equation[0].equals("==") || equation[0].equals("||")
        || equation[0].equals("?") || equation[equation.length-1].equals("&&")
        || equation[equation.length-1].equals("==") || equation[equation.length-1].equals("||")
        || equation[equation.length-1].equals("?")) {}
        else
        {
            for (int i = 0; i < equation.length; i++)
            {
                if (equation[i].equals("&&") || equation[i].equals("==") || equation[i].equals("||")
                || equation[i].equals("?"))
                {
                    amountPoints++;
                    criticalPoints.add(i);
                }
            }
        }
    }

    private void collect()
    {

        for (int i = 0; i <= amountPoints; i++)
        {
            ArrayList<String> temp = new ArrayList<>();

            int starter, finisher;

            if (i == 0)
            {
                starter = 0;
                finisher = criticalPoints.get(i);
            }
            else if (i == amountPoints)
            {
                starter = criticalPoints.get(i-1) + 1;
                finisher = equation.length;
            }
            else
            {
                starter = criticalPoints.get(i-1) + 1;
                finisher = criticalPoints.get(i);
            }

            for (int j  = starter; j < finisher; j++) temp.add(equation[j]);

            rawValues.add(temp);

        }

    }

    private void actualizer()
    {
        for (int i = 0; i < rawValues.size(); i++)
        {
            String[] temp = new String[rawValues.get(i).size()];

            for (int j = 0; j < rawValues.get(i).size(); j++) temp[j] = rawValues.get(i).get(j);
            finalValues.add(Boolean.parseBoolean(new CompareArith(temp).getAnswer()));
        }
    }

    private Boolean finalResult()
    {
        Boolean result = null;

        for (int i = 0; i < amountPoints; i++)
        {
            if (result == null) result = finalValues.get(i);

            result = logicCalculator(equation[criticalPoints.get(i)], result, finalValues.get(i+1));
        }

        return result;
    }


    private Boolean logicCalculator(String operator, boolean first, boolean second)
    {
        Boolean result = null;

        if (operator.equals("&&"))
        {
            if (first == true && second == true) result = true;
            else result = false;
        }
        else if (operator.equals("=="))
        {
            if (first == second) result = true;
            else result = false;
        }
        else if (operator.equals("||"))
        {
            if (first == false && second == false) result = false;
            else result = true;
        }
        else if (operator.equals("?"))
        {
            if ((first == true && second == true) || (first == false && second == false)) result = false;
            else result = true;
        }

        return result;
    }

}
