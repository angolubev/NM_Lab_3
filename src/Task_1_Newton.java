public class Task_1_Newton
{
    private static double x;
    private static int n;
    private static double[] X;
    private static double[] Y;

    public static void Do(int _n, double[] _X, double _x)
    {
        n=_n;
        X=_X;
        x=_x;
        Y = CountY();

        double N = NewtonPolinom();
        System.out.println("\nN(x) = " + N);
        System.out.println("y(x) = " + f(x));
        System.out.println("delta L = " + Math.abs(N - f(x)));
    }

    private static double[] CountY()
    {
        double[] Y = new double[n];

        System.out.print("X: ");
        for(int i=0; i<n; i++) System.out.print(X[i] + " ");

        System.out.print("\nY: ");
        for(int i=0; i<n; i++)
        {
            Y[i] = f(X[i]);
            System.out.print(Y[i] + " ");
        }
        return Y;
    }

    private static double f(double x)
    {
        //return Math.sin(Math.PI*x/6);
        return Math.exp(x) + x;
    }

    private static double f(int n, int i, int j)
    {
        if(n==0) return (Y[i] - Y[j]) / (X[i] - X[j]);
        else return (f(n-1, i, j-1) - f(n-1, i+1, j)) / (X[i] - X[j]);
    }

    private static double k(int n)
    {
        double res = 1;
        for(int i=0; i<=n; i++)
        {
            res*=(x - X[i]);
        }
        return res;
    }

    private static String kString(int n)
    {
        String res = "";
        for(int i=0; i<=n; i++)
        {
            res+="(x - " + X[i] + ")";
        }
        return res;
    }

    private static double NewtonPolinom()
    {
        double N = f(X[0]) + (x- X[0])*f(0, 1, 0);
        System.out.print("\nN(x) = " + f(X[0])+ " +" + f(0, 1, 0) + kString(0) + " ");
        for(int i=1; i<n-1; i++)
        {
            double f = f(i, 0, i+1);
            System.out.print(f + kString(i) + " ");
            N+= k(i)*f;
        }
        return N;
    }
}
