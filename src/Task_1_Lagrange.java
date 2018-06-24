public class Task_1_Lagrange
{
    private static int n;
    private static double[] X;
    private static double[] Y;

    public static void Do(int _n, double[] _X, double x)
    {
        n = _n;
        X = _X;
        Y = CountY();

        double L = LagrangePolinom(x);
        System.out.println("\nL(x) = " + L);
        System.out.println("y(x) = " + f(x));
        System.out.println("delta L = " + Math.abs(L - f(x)));
    }

    private static double f(double x)
    {
        //return Math.log(x);
        return Math.exp(x) + x;
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

    private static double W(double x)
    {
        double res =1;
        for(int i=0; i<n; i++)
        {
            res*= x-X[i];
        }
        return res;
    }

    private static double W(int k)
    {
        double res =1;
        for(int i=0; i<n; i++)
        {
            if(i!=k) res*= X[k]-X[i];
        }
        return res;
    }

    private static String WString(int k)
    {
        String res = "";
        for(int i=0; i<n; i++)
        {
            if(i!=k)
            {
                if(X[i] >=0) res+="(x - " + X[i] + ")";
                else res+="(x + " + (-X[i]) + ")";
            }
        }
        return res;
    }

    private static double LagrangePolinom(double x)
    {
        double L=0;
        System.out.print("\nL(x) = ");
        for(int i=0; i<n; i++)
        {
            double k = Y[i]/W(i);
            if(k>=0 && i>0) System.out.print("+");
            System.out.print(Y[i]/W(i) + WString(i)+ " ");

            L+= (Y[i] * W(x)) / ((x - X[i]) * (W(i)));
        }
        return L;
    }
}
