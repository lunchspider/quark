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
    public int GetTotalRow(){
        return this.row;
    }
    public int GetTotalColumn(){
        return this.col;
    }
    public double[] GetRow(int n){
        if(n>row || n<1){
            return null;
        }
        else{
            return this.matrix[n-1];
        }
    }
    public double[] GetCol(int n){
        if(n>col|| n<1){
            return null;
        }
        else{
            double s[]= new double[this.col];
            for(int i = 0;i<row;i++)
                s[i]= this.matrix[i][n-1];
            return s;
        }
    }
    public void Values(int rows,int columns,double value){
        if(rows-1>this.row || columns-1>this.col|| rows<1||columns<1){
            return;
        }
        else{
            this.matrix[rows-1][columns-1] = value;
        }
    }
    public double ValuesAt(int n,int j){
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
    public double Det(Matrix y){

        return 0;
    }
}