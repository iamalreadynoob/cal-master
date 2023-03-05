package command;

import finder.FinderMain;

public class TerminalMain
{

    String answer = null;
    public TerminalMain(String[] command)
    {
        if (command[0].equals("sum")) answer = new SumCommand(command).getAnswer();
        else if (command[0].equals("find")) answer = new FinderMain(command).getAnswer();
    }


    public String getAnswer()
    {
        return answer;
    }

}
