package quark;
public class Matrix {
    private double matrix[][];
    private int row,col;
    public Matrix(int rows,int columns){
        this.row = rows;
        this.col = columns;
        this.matrix = new double[this.row][this.col];
    }
    public  Matrix(int order){
        // creates a square matrix of given order
        this(order,order);
    }
    public Matrix(double matr[][]){
        this(matr.length,matr[0].length);
        this.matrix = matr;
    }
    public Matrix(double matr[]){
        this(1,matr.length);
        for(int i =1;i<=this.col;i++){
            this.Value(1,i,matr[i-1]);
        }
    }
    public Matrix(int matr[][]){
        // creates the Matrix if an int array in given
        this(matr.length,matr[0].length);
        for(int i = 0;i<this.row;i++){
            for(int j = 0;j<this.col;j++){
                this.matrix[i][j] = matr[i][j];
            }
        }
    }
    public Matrix(int matr[]){
        // creates the Matrix if only one row is given
        this(1,matr.length);
        for(int i =1;i<=this.col;i++){
            this.Value(1,i,matr[i-1]);
        }
    }
    public int GetTotalRow(){
        return this.row;
    }
    public int GetTotalColumn(){
        return this.col;
    }
    public double[] PullRow(int n){
        if(n>row || n<1){
            throw new IllegalArgumentException("Dimensions out of bound.");
        }
        else{
            return this.matrix[n-1];
        }
    }
    public double[] PullCol(int n){
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
    public void PushRow(double GivenRow[]){
        //pushes a row at the end of the matrix
        if(this.col!= GivenRow.length){
            throw new IllegalArgumentException("Dimensions of the given row does not match the number of rows in the matrix.");
        }
        else{
            this.row++;
            double y[][] = new double[this.row][this.col];
            for(int i = 0;i<this.row-1;i++){
                for(int j = 0;j<this.col;j++){
                    y[i][j] = this.matrix[i][j];
                }
            }
            this.matrix = y;
            for(int i = 0;i<this.col;i++){
                this.matrix[this.row-1][i] = GivenRow[i];
            }
        }
    }
    public void PushColumn(double GivenColumn[]){
        if(this.row != GivenColumn.length){
            throw new IllegalArgumentException("Dimensions of the given row does not match the number of rows in the matrix.");
        }
        else{
            this.col++;
            double y[][] =new double[this.row][this.col];
            for(int i = 0;i<this.row;i++){
                for(int j = 0;j<this.col-1;j++){
                    y[i][j] = this.matrix[i][j];
                }
            }
            this.matrix = y;
            for(int i =0;i<this.row-1;i++){
                this.matrix[i][this.col-1] = GivenColumn[i];
            }
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
    public static Matrix IdentityMatrix(int n,int j){
        // Return a Identity Matrix of matrix of nxj where only diagonal elements are 1
        Matrix y = new Matrix(n,j);
        for(int i =1;i<=n;i++){
            for(int k = 1;k<=n;k++){
                if(i==k){ y.Value(i, j,1);}
            }
        }
        return y;
    }
    public Matrix SubMatrix(Matrix y,int StartRow,int StartColumn,int EndRow,int EndColumn){
        // returns a sub matrix of given order
        Matrix z = new Matrix(EndRow-StartColumn +1,EndColumn-StartColumn +1);
        for(int i = StartRow;i<=EndRow;i++){
            for(int j = StartColumn;j<=EndColumn;j++){
                z.Value(i-StartRow+1,j-StartColumn+1,y.Get(i,j));
            }
        }
        return z;
    }

    public double Det(Matrix y){

        return 0;
    }
}