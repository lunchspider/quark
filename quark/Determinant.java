package quark;
public class Determinant{
    public static double Det(Matrix x){
        if(x.GetTotalColumn()!=x.GetTotalRow()){
            throw new IllegalArgumentException("Can only find determinants of square matrices." + x +" is not a square matrix.");
        }
        if(x.GetTotalColumn()==2){
            return x.get(1, 1)*x.get(2, 2) - x.get(1,2)*x.get(2, 1);
        }
        double det = -1;
        Matrix y = quark.decompositors.LU.Decompose(x)[1];
        for(int i = 1;i<=y.GetTotalRow();i++){
            det *= y.get(i,i);
        }
        return det;
    }
}