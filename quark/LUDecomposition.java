package quark;
public class LUDecomposition{
	public static Matrix[] Decompose(Matrix v) throws Exception{
		int n = v.GetTotalRow();
		if(TotalRow!=v.GetTotalColumn()){
			throw new Exception("Can only decompose square matrix.");
		}
		double mat[][] = v.Pull();
		double down[][] = new double[n][n];
		double up[][] = new double[n][n];
		for(int i = 0;i<n;i++){
			down[i][i] =1;
		}
		up[0] = v.PullRow[i];
		for(int i =0;i<n;i++){
			for(int j =0;j<n;j++){
				down[j][i] = mat[j][i]/ up[i][i];
				
			}
		}
		return null;
	}
}