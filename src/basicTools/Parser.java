package basicTools;

import java.util.ArrayList;

public class Parser
{

    private String output;

    public Parser(String output)
    {
        this.output = output;
    }

    public ArrayList<String> parse()
    {
        ArrayList<String> parsed = new ArrayList<>();

        int location = 0;

        while (location < output.length())
        {
            String currentWord = null;

            while (location < output.length() && output.charAt(location) != ' ')
            {
               if (currentWord == null) currentWord = Character.toString(output.charAt(location));
               else currentWord += Character.toString(output.charAt(location));

               location++;
            }

            if (currentWord != null) parsed.add(currentWord);
            if (location < output.length() && output.charAt(location) == ' ') location++;
        }

        return parsed;
    }

}
