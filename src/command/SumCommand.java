package command;

public class SumCommand
{

    private String answer = null;

    protected SumCommand(String[] command)
    {
        if (command[2].equals("to"))
        {
            try
            {
                double starter = Double.parseDouble(command[1]);
                double finisher = Double.parseDouble(command[3]);

                double increaser = 1;
                if (command.length > 4 && command[4].equals("by")) increaser = Integer.parseInt(command[5]);

                double result = 0;
                for (double i = starter; i <= finisher; i += increaser) result += i;
                answer = Integer.toString((int) result);

            }catch (Exception e) {}
        }
    }

    protected String getAnswer() {return answer;}

}
