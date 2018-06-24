import javafx.util.Pair;

public class Task_2
{
    static Vector x;
    static Vector f;
    static int n;

    static Vector a, b, c, d;

    public static void Do(Vector _x, Vector _f, double X)
    {
        x=_x;
        f=_f;
        n=x.Length();

        Pair<Matrix, Vector> p = GetSystemData();
        Matrix A = p.getKey();
        Vector z = p.getValue();

        Vector t = Method_Progonki.Do(A, z);
        c=new Vector(n-1);
        c.Set(0,0);
        for(int i=1;i<n-1; i++) c.Set(i, t.Get(i-1));

        FindABCD();
        System.out.println("f(x) = " + CountSpline(X));
    }

    private static double h(int i)
    {
        return x.Get(i) - x.Get(i-1);
    }

    private static Pair<Matrix, Vector> GetSystemData()
    {
        Matrix A = new Matrix(n-2, n-2, 0);
        Vector b = new Vector(n-2);

        A.Set(0,0, 2*(h(1) + h(2)));
        A.Set(0, 1, h(2));
        b.Set(0, 3*(((f.Get(2) - f.Get(1)) / h(2)) - ((f.Get(1) - f.Get(0)) / h(1))) );

        int j=0;
        for(int i=3; i<n-1; i++)
        {
            A.Set(i-2, j, h(i-1));
            A.Set(i-2, j+1, 2*(h(i-1) + h(i)));
            A.Set(i-2, j+2, h(i));
            b.Set(i-2, 3*(((f.Get(i) - f.Get(i-1)) / h(i)) - ((f.Get(i-1) - f.Get(i-2)) / h(i-1))));

            j++;
        }

        A.Set(n-3, j, h(n-2));
        A.Set(n-3, j+1, 2*(h(n-2) + h(n-1)));
        b.Set(n-3, 3*(((f.Get(n-1) - f.Get(n-2)) / h(n-1)) - ((f.Get(n-2) - f.Get(n-3)) / h(n-2))));

        return new Pair<>(A, b);
    }

    private static void FindABCD()
    {
        int N = n-1;

        a=new Vector(N);
        b=new Vector(N);
        d=new Vector(N);

        for(int i=1; i<N; i++)
        {
            a.Set(i-1, f.Get(i-1));
            b.Set(i-1, ( (f.Get(i) - f.Get(i-1)) / h(i)) - ((h(i)*(c.Get(i) + 2*c.Get(i-1)))/3) );
            d.Set(i-1, (c.Get(i) - c.Get(i-1)) / (3*h(i)));
        }

        a.Set(N-1, f.Get(N-1));
        b.Set(N-1, ( ((f.Get(N) - f.Get(N-1)) / h(N-1)) - 2*h(N-1)*c.Get(N-1)/3 ));
        d.Set(N-1, (-c.Get(N-1))/(3*h(N-1)));
    }

    private static double CountSpline(double X)
    {


        for(int i=0; i<n-1;i++)
        {
            String s = "(x - " + x.Get(i) + ")";
            System.out.println("Spline: " + a.Get(i) + "+ " + b.Get(i) + s + "+" + c.Get(i) + s + "^2 + " + d.Get(i) + s + "^3");
        }


        int i;
        double k;
        for(k=x.Get(0), i=0; k<=x.Get(n-1); i++, k++)
        {
            if ((k<=X)&&(k>=X-1)) break;
        }

        double t = X-x.Get(i);
        return a.Get(i) + b.Get(i)*t + c.Get(i)*t*t + d.Get(i)*t*t*t;
    }
}