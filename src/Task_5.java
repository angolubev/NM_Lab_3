public class Task_5
{
    private static Vector x;
    private static Vector y;
    private static int n;

    public static void Do(double x0, double xk, double h1, double h2)
    {
        double rectangle_h1, trapeze_h1, sympson_h1;
        double rectangle_h2, trapeze_h2, sympson_h2;
        double runge_romberg_rectangle, runge_romberg_trapeze, runge_romberg_sympson;

        CountVectors(x0, xk, h1);
        rectangle_h1 = RectangleMethod(h1);
        trapeze_h1 = TrapezeMethod(h1);
        sympson_h1 = SympsonMethod(h1);

        System.out.println("h = " + h1);
        System.out.println("Rectangle method: " + rectangle_h1);
        System.out.println("Trapeze method: " + trapeze_h1);
        System.out.println("Sympson method: " + sympson_h1);

        CountVectors(x0, xk, h2);
        rectangle_h2 = RectangleMethod(h2);
        trapeze_h2 = TrapezeMethod(h2);
        sympson_h2 = SympsonMethod(h2);

        System.out.println("h = " + h2);
        System.out.println("Rectangle method: " + rectangle_h2);
        System.out.println("Trapeze method: " + trapeze_h2);
        System.out.println("Sympson method: " + sympson_h2);

        runge_romberg_rectangle = RungeRombergMethod(rectangle_h2, rectangle_h1, 2);
        runge_romberg_trapeze = RungeRombergMethod(trapeze_h2, trapeze_h1, 2);
        runge_romberg_sympson = RungeRombergMethod(sympson_h2, sympson_h1, 2);
        System.out.println("RungeRombergRectangle: " + runge_romberg_rectangle);
        System.out.println("RungeRombergTrapeze: " + runge_romberg_trapeze);
        System.out.println("RungeRombergSympson: " + runge_romberg_sympson);

        // для погрешности нужно посчитать точное значение
        System.out.println("Errors: ");
        System.out.println("Rectangle method: " + (runge_romberg_rectangle - rectangle_h2));
        System.out.println("Trapeze method: " + (runge_romberg_trapeze - trapeze_h2));
        System.out.println("Sympson method: " + (runge_romberg_sympson - sympson_h2));
    }

    private static void CountVectors(double x0, double xk, double h)
    {
        n = (int)((xk - x0)/h) + 1;
        x=new Vector(n);
        y=new Vector(n);

        double t = x0;
        for(int i=0; i<n; i++)
        {
            x.Set(i, t);
            y.Set(i, f(t));
            t+=h;
        }
    }

    private static double f(double x)
    {
        //return x / ((3*x+4)*(3*x+4));
        return 1/(256 - x*x*x*x);
    }

    private static double RectangleMethod(double h)
    {
        double sum =0;
        for(int i=1; i<n; i++)
        {
            sum += f((x.Get(i-1) + x.Get(i)) / 2);
        }
        return h*sum;
    }

    private static double TrapezeMethod(double h)
    {
        double sum = (y.Get(0) + y.Get(n-1)) / 2;
        for(int i=1; i<n-1; i++)
        {
            sum += y.Get(i);
        }
        return h*sum;
    }

    private static double SympsonMethod(double h)
    {
        double sum = y.Get(0) + y.Get(n-1);
        for(int i=1; i<n-1; i++)
        {
            if(i%2==0) sum += 2*y.Get(i);
            else sum += 4*y.Get(i);
        }
        return h*sum/3;
    }

    private static double RungeRombergMethod(double Fh, double Fkh, double p)
    {
        //double k = Fkh/Fh;
        return Fh + ((Fh - Fkh) / (Math.pow(2,p) - 1));
    }
}
