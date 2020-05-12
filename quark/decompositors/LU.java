package quark.decompositors;
import quark.*;
import static java.util.stream.IntStream.range;
public class LU {
	private static double[][] pivotize(double[][] m) {
        int n = m.length;
        double[][] id = range(0, n).mapToObj(j -> range(0, n)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);
 
        for (int i = 0; i < n; i++) {
            double maxm = m[i][i];
            int row = i;
            for (int j = i; j < n; j++)
                if (m[j][i] > maxm) {
                    maxm = m[j][i];
                    row = j;
                }
 
            if (i != row) {
                double[] tmp = id[i];
                id[i] = id[row];
                id[row] = tmp;
            }
        }
        return id;
    }
	public static Matrix[] Decompose(Matrix v) {
		int n = v.GetTotalRow();
		if (n != v.GetTotalColumn()) {
			throw new IllegalArgumentException("Can only decopose a square matrix");
		}
		Matrix p = new Matrix(pivotize(v.Pull()));
		v = Operations.Multiply(p,v);
		double mat[][] = v.Pull();
		double down[][] = new double[n][n];
		double up[][] = new double[n][n];
		for (int j = 0; j < n; j++) {
            down[j][j] = 1;
            for (int i = 0; i < j + 1; i++) {
                double s1 = 0;
                for (int k = 0; k < i; k++)
                    s1 += up[k][j] * down[i][k];
                up[i][j] = mat[i][j] - s1;
            }
            for (int i = j; i < n; i++) {
                double s2 = 0;
                for (int k = 0; k < j; k++)
                    s2 += up[k][j] * down[i][k];
                down[i][j] = (mat[i][j] - s2) / up[j][j];
            }
		}
		Matrix[] lu = {new Matrix(down),new Matrix(up),p};
		return lu;
	}
}