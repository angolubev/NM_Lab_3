import javafx.util.Pair;

public class Task_3
{
    private static int N;
    private static Vector x;
    private static Vector y;

    public static void Do(Vector _x, Vector _y)
    {
        N = _x.Length();
        x=_x;
        y=_y;

        Vector a = GetSystem1().Solve();
        System.out.println("F1(x) = " + F1xString(a));
        System.out.println("Fi = " + Fi1(a));

        a = GetSystem2().Solve();
        System.out.println("F2(x) = " + F2xString(a));
        System.out.println("Fi = " + Fi2(a));
    }

    private static LinearSystem GetSystem1()
    {
        Matrix A = new Matrix(2, 2, 0);
        Vector b = new Vector(2, 0);

        A.Set(0,0, N);
        for(int i=0; i<N; i++)
        {
            double X = x.Get(i);
            double Y = y.Get(i);

            A.Increment(0,1, X);
            A.Increment(1, 0, X);
            A.Increment(1, 1, X*X);
            b.Increment(0, Y);
            b.Increment(1, X*Y);
        }

        return new LinearSystem(A,b);
    }

    private static LinearSystem GetSystem2()
    {
        Matrix A = new Matrix(3, 3, 0);
        Vector b = new Vector(3, 0);

        A.Set(0,0, N);
        for(int i=0; i<N; i++)
        {
            double X = x.Get(i);
            double Y = y.Get(i);

            A.Increment(0,1, X);
            A.Increment(0, 2, X*X);

            A.Increment(1, 0, X);
            A.Increment(1, 1, X*X);
            A.Increment(1, 2, X*X*X);

            A.Increment(2, 0, X*X);
            A.Increment(2, 1, X*X*X);
            A.Increment(2, 2, X*X*X*X);

            b.Increment(0, Y);
            b.Increment(1, X*Y);
            b.Increment(2, X*X*Y);
        }

        return new LinearSystem(A,b);

    }

    private static double F1x(Vector a, double x)
    {
        return a.Get(0) + a.Get(1)*x;
    }

    private static double F2x(Vector a, double x)
    {
        return a.Get(0) + a.Get(1)*x + a.Get(2)*x*x;
    }

    private static String F1xString(Vector a)
    {
        return a.Get(0) + " + " + a.Get(1) + "x";
    }

    private static String F2xString(Vector a)
    {
        return a.Get(0) + " + " + a.Get(1) + "x + " + a.Get(2) + "x^2";
    }

    private static double Fi1(Vector a)
    {
        double res =0;
        for(int i=0; i<N; i++) res+=(F1x(a, x.Get(i)) - y.Get(i)) * (F1x(a, x.Get(i)) - y.Get(i));
        return res;
    }

    private static double Fi2(Vector a)
    {
        double res =0;
        for(int i=0; i<N; i++) res+=(F2x(a, x.Get(i)) - y.Get(i)) * (F2x(a, x.Get(i)) - y.Get(i));
        return res;
    }
}
