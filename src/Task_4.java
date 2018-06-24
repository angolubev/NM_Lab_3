public class Task_4
{
    private static Vector x;
    private static Vector y;
    private static double x0;
    private static int n;
    private static int i;

    public static void Do(Vector _x, Vector _y, double _x0)
    {
        x = _x;
        y = _y;
        x0= _x0;
        n=x.Length();

        for(i=0; i<n; i++) if ((x.Get(i)<=x0) && (x0 <=x.Get(i+1))) break;

        System.out.println("y'(x0) = " + FirstDerivative());
        System.out.println("y''(x0) = " + SecondDerivative());
    }

    private static double FirstDerivative()
    {
        return (y.Get(i+1) - y.Get(i)) / (x.Get(i+1) - x.Get(i));
    }

    private static double SecondDerivative()
    {
        double a = (y.Get(i+2) - y.Get(i+1)) / (x.Get(i+2) - x.Get(i+1));
        double b = (y.Get(i+1) - y.Get(i)) / (x.Get(i+1) - x.Get(i));
        double c = x.Get(i+2) - x.Get(i);
        return 2.0000 * (a - b) /c;
    }
}
