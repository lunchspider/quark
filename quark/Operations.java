package quark;

public class Operations{
    public static Matrix Add(Matrix...v){
        // Adds two matrices with same order 
        try{
            int ColCount = v[0].GetTotalColumn();
            int RowCount = v[0].GetTotalRow();
            Matrix y = new Matrix(RowCount,ColCount);
            for(Matrix x :v){
                if(x.GetTotalColumn()==ColCount && x.GetTotalRow()== RowCount){
                    for(int i = 1; i<= RowCount;i++){
                        for(int j =  1;j<= ColCount;j++){
                            y.Value(i, j,y.Get(i, j) + x.Get(i, j));
                        }
                    }
                }
                else{
                    return null;
                }
            }
            return y;
        } catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Can only add matrices of same order.");
        }
    }
    public static Matrix Add(double k,Matrix v){
    	// adds <code> double k</code> to every number of the matrix
    	try{
    		for(int i = 1;i<=v.GetTotalRow();i++){
    			for(int j =1;j<=v.GetTotalColumn();j++){
    				v.Value(i,j,v.Get(i,j)+k);
    			}
    		}
    		return v;
    	} catch(ArrayIndexOutOfBoundsException e){
    		throw new ArrayIndexOutOfBoundsException("Can only add matrices of same order.");
    	}
    }
    public static Matrix Multiply(double k,Matrix x){
    	// multiply the given number to each and element of the matrix
        for(int i =1; i<=x.GetTotalRow();i++){
            for(int j = 1;j<=x.GetTotalColumn();j++){
                x.Value(i, j,x.Get(i, j) * k);
            }
        }
        return x;
    }
    public static Matrix Multiply(Matrix x,Matrix y){
        try{
            Matrix Product = new Matrix(x.GetTotalRow(),y.GetTotalColumn());
            for(int i = 1;i<=Product.GetTotalRow();i++){
                for(int j = 1;j<=Product.GetTotalColumn();j++){
                    for(int k = 1;k<=x.GetTotalColumn();k++){
                        Product.Value(i, j, Product.Get(i, j) + x.Get(i, k) * y.Get(k, j));
                    }
                }
            }
            return Product;
        } catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Give valid matrices for multiplications.");
        }
    }
    public static Matrix Multiply(Matrix...v){
        Matrix Product= Multiply(v[0],v[1]);
        for(int l= 2;l<v.length;l++){
            // multiplying product with v[l] from v[2] to v's lenght
            Product =Multiply(Product, v[l]);
        }
       return Product;
    }
    public static Matrix ScalerDivide(double k,Matrix v){
    	double y[][] = v.Pull();
    	for(int i =0;i<v.GetTotalRow();i++){
    		for(int j=0;j<v.GetTotalColumn();j++){
    			y[i][j] = k/y[i][j];
    		}
    	}
    	return new Matrix(y);
    }
    public static Matrix ScalerDivide(Matrix v, double k){
    	double y[][] = v.Pull();
    	for(int i =0;i<v.GetTotalRow();i++){
    		for(int j=0;j<v.GetTotalColumn();j++){
    			y[i][j]/= k;
    		}
    	}
    	return new Matrix(y);
    }
}