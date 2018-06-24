public class LinearSystem
{
    private Matrix matrix;
    private Vector vector;

    public LinearSystem(Matrix A, Vector b)
    {
        matrix = A;
        vector = b;
    }

    public Vector Solve()
    {
        return LUMethod.Do(matrix, vector);
    }
}
