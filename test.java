import quark.*;
public class test {
    public static void main(String[] args)
    {
        Matrix matr = new Matrix(3);
        Matrix ln = new Matrix(3);
        for(int i = 1;i<=3;i++){
            for(int j = 1;j<=3;j++){
                matr.Values(i, j,i+j);
                ln.Values(i, j,i+j);
            }
        }
        Matrix y = Matrix.Multiply(matr,ln);
        for(int i = 1;i<=3;i++){
            for(int j = 1;j<=3;j++){
                System.out.println(y.ValuesAt(i, j));
            }
        }
    }


}
