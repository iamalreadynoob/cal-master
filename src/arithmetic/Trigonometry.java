package arithmetic;

public class Trigonometry
{

    private Double result = null;
    private double degree;

    public Trigonometry(String command, double degree)
    {
        this.degree = degree;

        if (command.equals("sin")) result = Math.sin(Math.toRadians(degree));
        else if (command.equals("cos")) result = Math.cos(Math.toRadians(degree));
        else if (command.equals("tan")) result = Math.tan(Math.toRadians(degree));
        else if (command.equals("cot")) result = cot();
        else if (command.equals("sec")) result = sec();
        else if (command.equals("csc")) result = csc();
        else if (command.equals("arcsin")) result = Math.toDegrees(Math.asin(degree));
        else if (command.equals("arccos")) result = Math.toDegrees(Math.acos(degree));
        else if (command.equals("arctan")) result = Math.toDegrees(Math.atan(degree));
        else if (command.equals("arccot")) result = acot();
        else if (command.equals("arcsec")) result = asec();
        else if (command.equals("arccsc")) result = acsc();
    }

    public double getResult() {return result;}

    private double cot()
    {
        double cotangent = 1 / Math.tan(Math.toRadians(degree));
        return cotangent;
    }

    private double sec()
    {
        double secant = 1 / Math.cos(Math.toRadians(degree));
        return secant;
    }

    private double csc()
    {
        double cosecant = 1 / Math.sin(Math.toRadians(degree));
        return cosecant;
    }

    private double acot()
    {
        double toOne = 1 - degree;
        double arctangent = Math.toDegrees(Math.atan(toOne));
        double arccotangent = 90 - arctangent;
        return arccotangent;
    }

    private double asec()
    {
        double arcsecant = Math.toDegrees(Math.acos(1 / degree));
        return arcsecant;
    }

    private double acsc()
    {
        double arccosecant = Math.toDegrees(Math.asin(1 / degree));
        return arccosecant;
    }

}
