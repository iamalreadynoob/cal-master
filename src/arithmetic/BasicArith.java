package arithmetic;

import basicTools.Predefinitions;

import java.util.ArrayList;

public class BasicArith
{
    String answer;
    public BasicArith(String[] equation)
    {
        if (equation != null)
        {
            ArrayList<String> symbols = new ArrayList<>();
            ArrayList<Double> numbers = new ArrayList<>();
            int remain;

            if (equation[0].equals("-") || equation[0].equals("+"))
            {
                remain = 0;
            }

            else
            {
                symbols.add("+");
                remain = 1;
            }

            for (int i = 0; i < equation.length; i++)
            {
                if ((i+2) % 2 == remain) symbols.add(equation[i]);
                else
                {
                    try
                    {
                        numbers.add(Double.parseDouble(equation[i]));
                    }
                    catch (Exception e)
                    {
                        if (new Predefinitions().isConstant(equation[i]))
                        {
                            numbers.add(new Predefinitions().getConstants().get(equation[i]));
                        }

                        else if (equation[i].charAt(equation[i].length() - 1) == '!')
                        {
                            String temp = null;
                            double number;

                            for (int j = 0; j < equation[i].length()-1; j++)
                            {
                                if (temp == null) temp = Character.toString(equation[i].charAt(j));
                                else temp += Character.toString(equation[i].charAt(j));
                            }

                            number = Double.parseDouble(temp);

                            if (number >= 0)
                            {
                                if (number == 0) numbers.add((double)1);
                                else
                                {
                                    double tempResult = 1;

                                    for (int j = (int) number; j > 0; j--)
                                    {
                                        tempResult *= j;
                                    }

                                    numbers.add(tempResult);
                                }
                            }
                        }

                        else if (equation[i].contains("^"))
                        {
                            Double exp = exponenter(equation[i]);

                            if (exp != null) numbers.add(exp);
                        }

                        else if (equation[i].contains("#"))
                        {
                            int rootSymAmount = 0;
                            int rootLoc = 0;
                            for (int j = 0; j < equation[i].length(); j++)
                            {
                                if (equation[i].charAt(j) == '#')
                                {
                                    rootSymAmount++;
                                    rootLoc = j;
                                }

                                if (rootSymAmount > 1) break;
                            }

                            if (rootSymAmount == 1)
                            {
                                if (rootLoc == 0)
                                {
                                    String baseS = null;

                                    for (int j = rootLoc+1; j < equation[i].length(); j++)
                                    {
                                        if (baseS == null) baseS = Character.toString(equation[i].charAt(j));
                                        else baseS += Character.toString(equation[i].charAt(j));
                                    }

                                    numbers.add(Math.pow(Double.parseDouble(baseS), 0.5));
                                }
                                else if (rootLoc != equation[i].length()-1)
                                {
                                    String powerS = null;
                                    String baseS = null;

                                    for (int j = 0; j < rootLoc; j++)
                                    {
                                        if (powerS == null) powerS = Character.toString(equation[i].charAt(j));
                                        else powerS += Character.toString(equation[i].charAt(j));
                                    }

                                    for (int j = rootLoc+1; j < equation[i].length(); j++)
                                    {
                                        if (baseS == null) baseS = Character.toString(equation[i].charAt(j));
                                        else baseS += Character.toString(equation[i].charAt(j));
                                    }

                                    numbers.add(Math.pow(Double.parseDouble(baseS), 1 / Double.parseDouble(powerS)));
                                }
                            }
                        }

                        else if (new Predefinitions().isTrig(equation[i]))
                        {
                            numbers.add(new Trigonometry(equation[i], Double.parseDouble(equation[i+1])).getResult());
                            i++;
                        }

                    }
                }
            }

            ArrayList<Double> remainedValues = numbers;
            ArrayList<String> remainedSym = symbols;

            boolean didChangeAnything = true;
            while (didChangeAnything)
            {
                didChangeAnything = false;

                for (int i = 0; i < remainedSym.size(); i++)
                {
                    if (remainedSym.get(i).equals("*"))
                    {
                        didChangeAnything = true;
                        double temp = remainedValues.get(i-1) * remainedValues.get(i);

                        remainedValues.set(i-1, temp);
                        remainedValues.remove(i);
                        remainedSym.remove(i);

                    }

                    else if (remainedSym.get(i).equals("/"))
                    {
                        didChangeAnything = true;
                        double temp = remainedValues.get(i-1) / remainedValues.get(i);

                        remainedValues.set(i-1, temp);
                        remainedValues.remove(i);
                        remainedSym.remove(i);
                    }

                    else if (remainedSym.get(i).equals("^"))
                    {
                        didChangeAnything = true;
                        double temp = Math.pow(remainedValues.get(i-1), remainedValues.get(i));

                        remainedValues.set(i-1, temp);
                        remainedValues.remove(i);
                        remainedSym.remove(i);
                    }

                    else if (remainedSym.get(i).equals("%"))
                    {
                        didChangeAnything = true;
                        double temp = remainedValues.get(i-1) % remainedValues.get(i);

                        remainedValues.set(i-1, temp);
                        remainedValues.remove(i);
                        remainedSym.remove(i);
                    }

                    else if (remainedSym.get(i).equals("#"))
                    {
                        didChangeAnything = true;
                        double temp = Math.pow(remainedValues.get(i), 1 / remainedValues.get(i-1));

                        remainedValues.set(i-1, temp);
                        remainedValues.remove(i);
                        remainedSym.remove(i);
                    }
                }
            }

            double result;

            if (remainedSym.isEmpty())
            {
                result = remainedValues.get(0);
            }
            else
            {
                result = 0;

                for (int i = 0; i < remainedValues.size(); i++)
                {
                    if (remainedSym.get(i).equals("+")) result += remainedValues.get(i);
                    else if (remainedSym.get(i).equals("-")) result -= remainedValues.get(i);
                }
            }

            if (result - (double)(Math.round(result)) == 0)
            {
                answer = Integer.toString((int) Math.round(result));
            }
            else answer = Double.toString(result);
        }

    }

    public String getAnswer() {return answer;}

    protected Double exponenter(String term)
    {
        Double expVal = null;
        Integer index = null;

        for (int i = 0; i < term.length(); i++)
        {
            if (term.charAt(i) == '^')
            {
                index = i;
                break;
            }
        }

        String sBase = null;
        String sPower = null;

        for (int i = 0; i < index; i++)
        {
            if (sBase == null) sBase = Character.toString(term.charAt(i));
            else sBase += Character.toString(term.charAt(i));
        }

        for (int i = index + 1; i < term.length(); i++)
        {
            if (sPower == null) sPower = Character.toString(term.charAt(i));
            else sPower += Character.toString(term.charAt(term.charAt(i)));
        }

        try
        {
            Double base = Double.parseDouble(sBase);
            Double power = Double.parseDouble(sPower);

            expVal = Math.pow(base, power);
        }catch (Exception e) {}

        return expVal;
    }

    public boolean isPrime(int number)
    {
        boolean isIt = true;

        if (number <= 1) isIt = false;

        else
        {
            for (int i = 2; i < number; i++)
            {
                if (number % i == 0)
                {
                    isIt = false;
                    break;
                }
            }
        }

        return isIt;
    }
}
