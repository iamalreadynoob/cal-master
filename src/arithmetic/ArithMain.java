package arithmetic;

public class ArithMain
{
    private String answer;

    public ArithMain(String[] equation)
    {
        if (isSimple(equation)) answer = new BasicArith(equation).getAnswer();
        else if (isComparison(equation)) answer = new CompareArith(equation).getAnswer();

    }

    public String getAnswer() {return answer;}

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
    private boolean isComparison(String[] equation)
    {
        boolean isIt = false;

        for (int i = 0; i < equation.length; i++)
        {
            if (equation[i].equals("<") || equation[i].equals(">") || equation[i].equals("<=")
            || equation[i].equals(">=") || equation[i].equals("=")) isIt = true;

            else if (equation[i].equals("&&") || equation.equals("==") || equation[i].equals("||")
            || equation[i].equals("?"))
            {
                isIt = false;
                break;
            }
        }

        return isIt;
    }

}
