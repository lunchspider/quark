import quark.*;
public class helloworld {
    public static void main(String[] args) {
        int k[][] = {{1,2,3},{4,5,6},{7,8,9}};
        Matrix y = new Matrix(k);
        y = Operations.Multiply(y, y);
        System.out.print(y.Print());
    }
}