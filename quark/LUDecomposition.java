package quark;

public class LUDecomposition {
	public static Matrix[] Decompose(Matrix v) {
		int n = v.GetTotalRow();
		if (n != v.GetTotalColumn()) {
			throw new IllegalArgumentException("Can only decopose a square matrix");
		}
		double mat[][] = v.Pull();
		double down[][] = new double[n][n];
		double up[][] = new double[n][n];
		int p =0;
		for(int i = 0;i<n;i++){
			down[i][i] =1;
		}
		up[0] = v.PullRow(1);
		for(int i =0;i<n;i++){
			for(int j =0;j<n;j++){
				down[j][i] = mat[j][i]/ up[i][i];
				if(i>1){
					for(int k = 0 ; k<j;k++){
						p += up[k][j]*down[i][k];
					}
					down[j][i] = (mat[j][i] - p)/ up[i][i];
					for(int k = 0;k<i;k++){
						p += up[k][j]* down[i][k];
					}
					up[i][j] = mat[i][j] - p;
				}
			}
		}
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				System.out.print(down[i][j] + " ");
			}
			System.out.println();
		}
		return null;
	}
}