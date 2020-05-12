package quark;
public class Determinant{
    public static double Det(Matrix x){
        if(x.GetTotalColumn()!=x.GetTotalRow()){
            throw new IllegalArgumentException("Can only find determinants of square matrices." + x +" is not a square matrix.");
        }
        if(x.GetTotalColumn()==2){
            return x.Get(1, 1)*x.Get(2, 2) - x.Get(1,2)*x.Get(2, 1);
        }
        double det = -1;
        Matrix y = quark.decompositors.LU.Decompose(x)[1];
        for(int i = 1;i<=y.GetTotalRow();i++){
            det *= y.Get(i,i);
        }
        return det;
    }
}