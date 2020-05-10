package quark;
import java.util.*;
import java.lang.Math;
public class Matrix implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
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
    public double[][] Pull(){
    	return matrix;
    }
    public int GetTotalRow(){
        return this.row;
    }
    public int GetTotalColumn(){
        return this.col;
    }
    public double[] PullRow(int n){
        // return a row of the given number
        CheckRow(n);
        return this.matrix[n-1];
    }
    public double[] PullCol(int n){
        // returns the column of given number
        CheckColumn(n);
        double s[]= new double[this.col];
        for(int i = 0;i<row;i++)
            s[i]= this.matrix[i][n-1];
        return s;
    }
    // changes a row at RowNumber to the given array
    public void PushRowAt(double[] r,int RowNumber){
        CheckRow(RowNumber);
        if(r.length!=this.row) throw new IllegalArgumentException("The array to be pushed must have same size as of the parent matrix.");
        this.matrix[RowNumber-1] = r;
    }
    public void PushColAt(double[] r ,int ColNumber){
        CheckColumn(ColNumber);
        if(r.length!=this.col) throw new IllegalArgumentException("The array to be pushed must have same size as parent matrix.");
        for(int i = 0;i<this.col;i++){
            this.matrix[i][ColNumber-1] = r[i];
        }
    }
    // sets the value of givenn row and column to val
    public void Value(int rows,int columns,double val){
        CheckRowAndCol(rows, columns);
        this.matrix[rows-1][columns-1] = val;
    }
    // return the element at nth row and jth column
    public double Get(int n,int j){
        CheckRowAndCol(n,j);
        return this.matrix[n-1][j-1];
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
    // returns a string of all the elements in a matrix seprated by every row.
    public String Print(){
        String str = new String();
        for(double[] a : this.matrix){
            str +=Arrays.toString(a);
            str+="\n";
        }
        return str;
    }
    // returns a string of the given Matrix
    public String toString(){
        String str ="[";
        for(double[] a : this.matrix){
            str += Arrays.toString(a);
        }
        str+="]";
        return str;
    }
    //return a long[][] whicn is rounded of version of the matrix
    public long[][] roundOff(){
        long[][] n = new long[this.row][this.row];
        for(int i = 0;i<this.row;i++){
            for(int j = 0;j<this.col;j++){
                n[i][j] = Math.round(this.matrix[i][j]);
            }
        }
        return n;
    }
    // rounds of the every element of the matrix
    public void round(){
        for(int i = 0;i<this.row;i++){
            for(int j = 0;j<this.col;j++){
                this.matrix[i][j] = Math.round(this.matrix[i][j]);
            }
        }
    }
    //check whether to matrices are equal or not.
    public boolean isEqual(Matrix y){
        if(y.GetTotalRow()!=this.row || this.col!=y.GetTotalColumn()){
            // since if order of the matrices are not equal they cannot be equal
            return false;
        }
        for(int i = 0;i<this.row;i++){
            for(int j = 0;j<this.col;j++){
                if(this.matrix[i][j] != y.matrix[i][j]) return false;
            }
        }
        return true;
    }
    // sorts a given row of the matrix
    public void sortRow(int RowNumber){
        CheckRow(RowNumber);
        Arrays.sort(this.matrix[RowNumber-1]);
    }
    // sorts a given column of the matrix
    public void sortCol(int ColNumber){
        try{
            Arrays.sort(this.matrix,Comparator.comparingDouble(a -> a[ColNumber-1]));
        } catch(Exception e){
            throw new IllegalArgumentException("Give a valid column number to sort."+ColNumber+ " is not valid.");
        }
    }
    // sorts each and every row of the matrix
    public void sortByRow(){
        for(int i=0;i<this.row;i++){
            Arrays.sort(this.matrix[i]);
        }
    }
    // sorts each and every column of the matrix
    public void sortByCol(){
       for(int i = 1;i<=this.col;i++){
           this.sortCol(i);
       }
    }
    public double Det(Matrix y){

        return 0;
    }
    private void CheckRow(int RowNumber){
        if(RowNumber>this.row || RowNumber<1){
            throw new IllegalArgumentException("Dimensions out of bound.");
        }
    }
    private void CheckColumn(int ColNumber){
        if(ColNumber>this.row || ColNumber<1){
            throw new IllegalArgumentException("Dimensions out of bound.");
        }
    }
    private void CheckRowAndCol(int RowNumber,int ColNumber){
        CheckRow(RowNumber);
        CheckColumn(ColNumber);
    }
}