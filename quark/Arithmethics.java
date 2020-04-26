package quark;

public class Arithmethics {
    public static Matrix Add(Matrix...v){
        // Adds two matrices with same order 
        int ColCount = v[0].GetTotalColumn();
        int RowCount = v[0].GetTotalRow();
        Matrix y = new Matrix(RowCount,ColCount);
        for(Matrix x :v){
            if(x.GetTotalColumn()==ColCount && x.GetTotalRow()== RowCount){
                for(int i = 1; i<= RowCount;i++){
                    for(int j =  1;j<= ColCount;j++){
                        y.Values(i, j,y.ValuesAt(i, j) + x.ValuesAt(i, j));
                    }
                }
            }
            else{
                return null;
            }
        }
        return y;
        
    }
    public static Matrix Multiply(double k,Matrix x){
        for(int i =1; i<=x.GetTotalRow();i++){
            for(int j = 1;j<=x.GetTotalColumn();j++){
                x.Values(i, j,x.ValuesAt(i, j) * k);
            }
        }
        return x;
    }
    public static Matrix Multiply(Matrix x,Matrix y){
        Matrix Product = new Matrix(x.GetTotalRow(),y.GetTotalColumn());
        for(int i = 1;i<=Product.GetTotalRow();i++){
            for(int j = 1;j<=Product.GetTotalColumn();j++){
                for(int k = 1;k<=x.GetTotalColumn();k++){
                    Product.Values(i, j, Product.ValuesAt(i, j) + x.ValuesAt(i, k) * y.ValuesAt(k, j));
                }
            }
        }
        return Product;
    }
    public static Matrix Multiply(Matrix...v){
        Matrix Product= Multiply(v[0],v[1]);
        for(int l= 2;l<v.length;l++){
            // multiplying product with v[l] from v[2] to v's lenght
            Product =Multiply(Product, v[l]);
        }
       return Product;
    }
}