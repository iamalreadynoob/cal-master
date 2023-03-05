package finder;

import arithmetic.BasicArith;

import java.util.ArrayList;

public class PolynomialFind
{

    private String answer = null;
    private ArrayList<String> equation = new ArrayList<>();
    private String respectedBy = null;
    private ArrayList<String> valPower = new ArrayList<>();
    private ArrayList<String> coefficient = new ArrayList<>();
    private ArrayList<String> sign = new ArrayList<>();

    protected PolynomialFind(String[] command)
    {
        respectedBy = command[2];
        simplyEquation(command);
        seperator();

        System.out.println(equation);
        System.out.println(sign);
        System.out.println(coefficient);
        System.out.println(valPower);
    }

    protected String getAnswer() {return answer;}

    private void simplyEquation(String[] command)
    {

        if (!(command[3].equals("+") || command[3].equals("-")))
        {
            equation.add("+");
        }

        Integer otherSide = null;
        for (int i = 3; i < command.length; i++)
        {
            if (command[i].equals("="))
            {
                otherSide = i;
                continue;
            }

            if (otherSide == null) equation.add(command[i]);
            else if (i == otherSide+1)
            {
                if (!(command[i].equals("+") || command[i].equals("-")))
                {
                    equation.add("-");
                    equation.add(command[i]);
                }
                else if (command[i].equals("+")) equation.add("-");
                else if (command[i].equals("-")) equation.add("+");
            }
            else if (command[i].equals("+")) equation.add("-");
            else if (command[i].equals("-")) equation.add("+");
            else equation.add(command[i]);
        }
    }

    private void seperator()
    {
        int location = 0;

        if (!(equation.get(location).equals("+") || equation.get(location).equals("-")))
        {
            sign.add("+");
            location++;
        }

        while (location < equation.size())
        {
            if (location < equation.size() && equation.get(location).equals("+"))
            {
                sign.add("+");
                location++;
            }
            else if (location < equation.size() && equation.get(location).equals("-"))
            {
                sign.add("-");
                location++;
            }

            if (!(equation.get(location).equals("x") && equation.get(location).equals("+")
            && equation.get(location).equals("-")))
            {
                ArrayList<String> coefficients = new ArrayList<>();
                boolean isCoefficient = true;

                while (true)
                {
                    coefficients.add(equation.get(location));
                    location++;

                    if (location >= equation.size() || equation.get(location).equals("+")
                    || equation.get(location).equals("-"))
                    {
                        isCoefficient = false;
                        break;
                    }
                    if (location + 1 < equation.size() && equation.get(location).equals("*")
                    && equation.get(location+1).equals(respectedBy))
                    {
                        location++;
                        break;
                    }
                }

                String[] coefArr = new String[coefficients.size()];
                for (int i = 0; i < coefficients.size(); i++) coefArr[i] = coefficients.get(i);
                String value = new BasicArith(coefArr).getAnswer();

                coefficient.add(value);
                if (!isCoefficient) valPower.add("0");
            }

            if (equation.get(location).equals("x"))
            {
                location++;
                if (location < equation.size())
                {
                    if (location + 1 < equation.size() && equation.get(location).equals("^"))
                    {
                        location++;
                        valPower.add(equation.get(location));
                    }
                    else valPower.add("1");
                }
                else valPower.add("1");
            }

        }
    }

}
