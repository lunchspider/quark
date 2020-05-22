package quark;
import java.io.*;
import java.util.*;
public class Matrix implements Serializable {
    private static final long serialVersionUID = 660376820408908112L;
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
        this.matrix = (double[][]) deepClone(matr);
    }
    /**
     * 
     * @param matr
     * makes a column matrix
     */
    public Matrix(double matr[]){
        this(matr.length,1);
        for(int i =0;i<this.col;i++)
            this.matrix[i][0] = matr[i];
    }
    /**
     * 
     * @param matr
     * @param isRowMatrix
     * Creates a row matrix.
     */
    public Matrix(double matr[],boolean isRowMatrix){
        this(1,matr.length);
        this.matrix[0] = (double[]) deepClone(matr);
    }
    public Matrix(int matr[][]){
        // creates the Matrix if an int array in given
        this(matr.length,matr[0].length);
        this.matrix = (double[][]) deepClone(matr);
    }

    public Matrix(int matr[]){
        this(matr.length,1);
        for(int i=0;i<this.col;i++)
            this.matrix[i][0] = matr[i];
    }

    public Matrix(int matr[],boolean isRowMatrix){
        this(matr.length,1);
        this.matrix[0] = (double[]) deepClone(matr);
    }
    public Matrix(long matr[][]){
        this(matr.length,matr[0].length);
        this.matrix = (double[][])deepClone(matr);
    }
    public Matrix(long matr[]){
        this(matr.length,1);
        for(int i = 0;i<this.col;i++) 
            this.matrix[0][i] = matr[i];
    }
    public Matrix(long matr[],boolean isRowMatrix){
        this(1,matr.length);
        this.matrix[0]= (double[]) deepClone(matr);
    }
    public Matrix(Matrix a){
        this(a.row,a.col);
        this.matrix = a.matrix;
    }
    public double[][] Pull(){
    	return (double[][])deepClone(this.matrix);
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
        return (double[]) deepClone(this.matrix[n-1]);
    }
    public double[] PullCol(int n){
        // returns the column of given number
        CheckColumn(n);
        double s[]= new double[this.col];
        for(int i = 0;i<row;i++)
            s[i]= this.matrix[i][n-1];
        return (double[]) deepClone(s);
    }
    // changes a row at RowNumber to the given array
    public void PushRowAt(double[] r,int RowNumber){
        CheckRow(RowNumber);
        if(r.length!=this.row) throw new IllegalArgumentException("The array to be pushed must have same size as of the parent matrix.");
        this.matrix[RowNumber-1] = (double[]) deepClone(r);
    }
    public void PushColAt(double[] r ,int ColNumber){
        CheckColumn(ColNumber);
        if(r.length!=this.col) throw new IllegalArgumentException("The array to be pushed must have same size as parent matrix.");
        for(int i = 0;i<this.col;i++){
            this.matrix[i][ColNumber-1] = r[i];
        }
    }
    // sets the set of givenn row and column to val
    public void set(int rows,int columns,double val){
        CheckRowAndCol(rows, columns);
        this.matrix[rows-1][columns-1] = val;
    }
    // return the element at nth row and jth column
    public double get(int n,int j){
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
            y.set(i, i, 1);
        }
        return y;
    }
    public static Matrix IdentityMatrix(int n,int j){
        // Return a Identity Matrix of matrix of nxj where only diagonal elements are 1
        Matrix y = new Matrix(n,j);
        for(int i =0;i<n;i++){
            for(int k = 0;k<n;k++){
                if(i==k){ y.matrix[i][j] = 1;}
            }
        }
        return y;
    }
    public Matrix SubMatrix(Matrix y,int StartRow,int StartColumn,int EndRow,int EndColumn){
        // returns a sub matrix of given order
        Matrix z = new Matrix(EndRow-StartColumn +1,EndColumn-StartColumn +1);
        for(int i = StartRow;i<=EndRow;i++){
            for(int j = StartColumn;j<=EndColumn;j++){
                z.set(i-StartRow+1,j-StartColumn+1,y.get(i,j));
            }
        }
        return z;
    }
    // returns a string of all the elements in a matrix seprated by every row.
    public String print(){
        String str = new String();
        for(double[] a : this.matrix){
            str +=Arrays.toString(a);
            str+="\n";
        }
        return str;
    }
    // returns a string of the given Matrix
    @Override
    public String toString(){
        String str ="[";
        for(int i=0;i<this.row;i++){
            str+= Arrays.toString(this.matrix[i]);
            if(i<this.row-1) str+=",";
        }
        str+="]";
        return str;
    }
    public double[][] toArray(){
        return this.Pull();
    }
    //return a Matrix whicn is rounded of version of the matrix
    public Matrix roundOff(){
        Matrix n = new Matrix(this.row,this.row);
        for(int i = 0;i<this.row;i++){
            for(int j = 0;j<this.col;j++){
                n.matrix[i][j] = Math.round(this.matrix[i][j]);
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
    public boolean equals(Matrix y){
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
    public double Det(){
        // returns the determinant of the matrix
        if(this.row!=this.col){
            throw new IllegalArgumentException("Determinant is only defined for square matrix."+ this +" is not a square matrix.");
        }
        return Math.round(Determinant.Det(this));
    }
    // transposes the Matrix
    public void Transpose(){
        this.Copy(Operations.Transpose(this));
    }
    // copies a matrix into another
    public void Copy(Matrix v){
        this.row = v.GetTotalRow();
        this.col = v.GetTotalColumn();
        this.matrix = (double[][])deepClone(v.Pull());
    }
    private void CheckRow(int RowNumber){
        if(RowNumber>this.row || RowNumber<1){
            throw new IllegalArgumentException("Dimensions out of bound." + RowNumber+" is a invalid row number for " + this +".");
        }
    }
    private void CheckColumn(int ColNumber){
        if(ColNumber>this.col || ColNumber<1){
            throw new IllegalArgumentException("Dimensions out of bound." + ColNumber + " is a invalid column number for " + this + ".");
        }
    }
    private void CheckRowAndCol(int RowNumber,int ColNumber){
        CheckRow(RowNumber);
        CheckColumn(ColNumber);
    }
    private static Object deepClone(Object object) {
        try {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ObjectOutputStream oos = new ObjectOutputStream(baos);
          oos.writeObject(object);
          ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
          ObjectInputStream ois = new ObjectInputStream(bais);
          return ois.readObject();
        }
        catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }
}