public class NM_Lab_3
{
    public static void main(String[] args)
    {
        int n =4;
        double[] Xa = {-2, -1, 0, 1};
        double[] Xb = {-2, -1, 0.2, 1};
        double x1 = -0.5;
        //Task_1_Lagrange.Do(n, Xb, x1);
        //Task_1_Newton.Do(n, Xb, x1);

        double[] x2 = {-2, -1, 0, 1, 2};
        double[] f = {-1.8647, -0.63212, 1, 3.7183, 9.3891};
        double X = -0.5;
        //Task_2.Do(new Vector(5, x2), new Vector(5, f), X);

        double[] x3 = {-3, -2, -1, 0, 1, 2};
        double[] y3 = {-2.9502, -1.8647, -0.63212, 1, 3.7183, 9.3891};
        //Task_3.Do(new Vector(6, x3), new Vector(6, y3));

        double[] x4 = {-0.2, 0, 0.2, 0.4, 0.6};
        double[] y4 = {-0.40136, 0, 0.40136, 0.81152, 1.2435};
        double x0 = 0.2;
        //Task_4.Do(new Vector(5, x4), new Vector(5, y4), x0);

        double X0 = -2;
        double Xk = 2;
        double h1 =1.0;
        double h2 = 0.5;
        Task_5.Do(X0, Xk, h1, h2);

    }
}

