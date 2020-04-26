package quark;
public class Matrix {
    private int matrix[][];
    private int row,col;
    public Matrix(int rows,int columns){
        this.row = rows;
        this.col = columns;
        this.matrix = new int[row][col];
    }
    public  Matrix(int order){
        // creates a square matrix of given order
        this(order,order);
    }
    public int GetTotalRow(){
        return this.row;
    }
    public int GetTotalColumn(){
        return this.col;
    }
    public int[] GetRow(int n){
        if(n>row || n<1){
            return null;
        }
        else{
            return this.matrix[n-1];
        }
    }
    public int[] GetCol(int n){
        if(n>col|| n<1){
            return null;
        }
        else{
            int s[]= new int[this.col];
            for(int i = 0;i<row;i++)
                s[i]= this.matrix[i][n-1];
            return s;
        }
    }
    public void Values(int rows,int columns,int value){
        if(rows-1>this.row || columns-1>this.col|| rows<1||columns<1){
            return;
        }
        else{
            this.matrix[rows-1][columns-1] = value;
        }
    }
    public int ValuesAt(int n,int j){
        if(n-1>this.row || j-1>this.col|| j<1||n<1){
            return 0;
        }
        else{
            return this.matrix[n-1][j-1];
        }
    }
    public static Matrix IndentityMatrix(int order){
        // return a matrix with all elements equal 1
        Matrix y = new Matrix(order);
        for(int i = 1;i<=order;i++){
            y.Values(i, i, 1);
        }
        return y;
    }
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
    public static Matrix Multiply(int k,Matrix x){
        for(int i =1; i<=x.GetTotalRow();i++){
            for(int j = 1;j<=x.GetTotalColumn();j++){
                x.Values(i, j,x.ValuesAt(i, j) * k);
            }
        }
        return x;
    }
    public static Matrix Multiply(Matrix...v){
       return null;
    }
}