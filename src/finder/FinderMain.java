package finder;

public class FinderMain
{

    private String answer = null;

    public FinderMain(String[] command)
    {
        if (command[1].equals("poly")) answer = new PolynomialFind(command).getAnswer();
    }

    public String getAnswer() {return answer;}

}
