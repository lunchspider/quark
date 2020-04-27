package quark;

public class Matrix {
    private double matrix[][];
    private int row,col;
    public Matrix(int rows,int columns){
        this.row = rows;
        this.col = columns;
        this.matrix = new double[row][col];
    }
    public  Matrix(int order){
        // creates a square matrix of given order
            this(order,order);
    }
    public Matrix(double m[][]){
        this.matrix = m;
        this.row = m.length;
        this.col = m[0].length;
    }
    public int GetTotalRow(){
        return this.row;
    }
    public int GetTotalColumn(){
        return this.col;
    }
    public double[] GetRow(int n){
        if(n>row || n<1){
            throw new IllegalArgumentException("Dimensions out of bound.");
        }
        else{
            return this.matrix[n-1];
        }
    }
    public double[] GetCol(int n){
        if(n>col|| n<1){
            throw new IllegalArgumentException("Dimensions out of bound.");
        }
        else{
            double s[]= new double[this.col];
            for(int i = 0;i<row;i++)
                s[i]= this.matrix[i][n-1];
            return s;
        }
    }
    public void Value(int rows,int columns,double val){
        if(rows-1>this.row || columns-1>this.col|| rows<1||columns<1){
            throw new IllegalArgumentException("Dimension out of bound.");
        }
        else{
            this.matrix[rows-1][columns-1] = val;
        }
    }
    public double Get(int n,int j){
        if(n-1>this.row || j-1>this.col|| j<1||n<1){
            throw new IllegalArgumentException("Dimensions out of bound.");
        }
        else{
            return this.matrix[n-1][j-1];
        }
    }
    public static Matrix IndentityMatrix(int order){
        // return a matrix with all elements equal 1
        Matrix y = new Matrix(order);
        for(int i = 1;i<=order;i++){
            y.Value(i, i, 1);
        }
        return y;
    }
    // Return a Identity Matrix of matrix of nxj where only diagonal elements are 1
    public static Matrix IdentityMatrix(int n,int j){
        Matrix y = new Matrix(n,j);
        for(int i =1;i<=n;i++){
            for(int k = 1;k<=n;k++){
                if(i==k){ y.Value(i, j,1);}
            }
        }
        return y;
    }
    public double Det(Matrix y){

        return 0;
    }
}