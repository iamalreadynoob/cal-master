package finder;

import arithmetic.BasicArith;

import java.math.BigInteger;
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

        if (isSuitable())
        {
            double p = getP();
            double q = getQ();
            ArrayList<Integer> factorizedP = factorize((int) Math.abs(p));
            ArrayList<Integer> factorizedQ = factorize((int) Math.abs(q));

            System.out.println(factorizedP);
            System.out.println(factorizedQ);
        }
    }

    protected String getAnswer() {return answer;}

    private void simplyEquation(String[] command)
    {

        if (!(command[3].equals("+") || command[3].equals("-"))) equation.add("+");

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

        for (int i = 0; i < equation.size(); i++) if (equation.get(i).equals(respectedBy)) coefficient.add("1");
        int varLocation = 0;

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

            if (equation.get(location).equals(respectedBy)) varLocation++;

            if (!(equation.get(location).equals(respectedBy) || equation.get(location).equals("+")
            || equation.get(location).equals("-")))
            {
                ArrayList<String> coefficients = new ArrayList<>();
                boolean isCoefficient = true;

                if (location - 1 > 0 && equation.get(location-1).equals("^"))
                {
                    isCoefficient = false;
                    location++;
                }

                while (isCoefficient)
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
                System.out.println(coefficients);

                if (!coefficients.isEmpty())
                {
                    String[] coefArr = new String[coefficients.size()];
                    for (int i = 0; i < coefficients.size(); i++) coefArr[i] = coefficients.get(i);
                    String value = new BasicArith(coefArr).getAnswer();


                    if (isCoefficient)
                    {
                        coefficient.set(varLocation, value);
                    }
                    else
                    {
                        valPower.add("0");
                        coefficient.add("1");

                        for (int i = coefficients.size()-2; i >= varLocation; i--) coefficient.set(i+1, coefficient.get(i));
                        coefficient.set(varLocation, value);
                    }

                    varLocation++;
                }
            }

            if (location < equation.size() && equation.get(location).equals("x"))
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

    private boolean isSuitable()
    {
        boolean isIt = true;

        for (int i = 0; i < coefficient.size(); i++)
        {
            if (Double.parseDouble(coefficient.get(i)) != (int)Double.parseDouble(coefficient.get(i)))
            {
                double floating = Double.parseDouble(coefficient.get(i)) - (int) Double.parseDouble(coefficient.get(i));
                double required = 1 / floating;

                if (required == (int) required)
                {
                    for (int j = 0; j < coefficient.size(); j++)
                    {
                        double currentVal = Double.parseDouble(coefficient.get(j));
                        coefficient.set(j, Double.toString(currentVal * required));
                    }
                }
                else
                {
                    isIt = false;
                    break;
                }
            }
        }

        return isIt;
    }

    private double getP()
    {
        double p = 0;

        for (int i = 0; i < valPower.size(); i++)
        {
            if (valPower.get(i).equals("0"))
            {
                if (sign.get(i).equals("+")) p += Double.parseDouble(coefficient.get(i));
                else p -= Double.parseDouble(coefficient.get(i));
            }
        }

        return p;
    }

    private double getQ()
    {
        int highestPow = 0;
        double q = 0;

        for (int i = 0; i < valPower.size(); i++)
        {
            if (highestPow < Integer.parseInt(valPower.get(i))) highestPow = Integer.parseInt(valPower.get(i));
        }

        for (int i = 0; i < valPower.size(); i++)
        {
            if (valPower.get(i).equals(Integer.toString(highestPow)))
            {
                if (sign.get(i).equals("+")) q += Double.parseDouble(coefficient.get(i));
                else q -= Double.parseDouble(coefficient.get(i));
            }
        }

        return q;
    }

    private ArrayList<Integer> factorize(int number)
    {
        // TODO: Find the problem
        ArrayList<Integer> factorized = new ArrayList<>();
        double value = number;
        double divideBy = number;

        factorized.add(1);
        while (value != 1 && divideBy >= 1)
        {
            if (new BasicArith(null).isPrime((int) divideBy))
            {
                if (value / divideBy == (int)(value / divideBy))
                {
                    boolean isExist = false;

                    for (int i = 0; i < factorized.size(); i++)
                    {
                        if (factorized.get(i) == value / divideBy)
                        {
                            isExist = true;
                            break;
                        }
                    }

                    if (!isExist) factorized.add((int) divideBy);

                    value /= divideBy;
                }
            }

            divideBy--;
        }

        return factorized;
    }

}
