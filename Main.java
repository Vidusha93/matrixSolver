import java.util.*;
class Matrix {
    private final int mSize;
    Matrix(int n){
        this.mSize = n;
    }
    void printMatrix(double[][] A,double[] B) {
        System.out.print("  -*-    ");
        for(int i = 1; i <= mSize; i++) {
            System.out.print("X" + i);
            System.out.print("  ");
        }
        System.out.println("\t B");
        System.out.println();
        for (int i = 0; i < mSize; i++) {
            System.out.print("  -" + (i+1) + "-   ");
            for (int j = 0; j < mSize; j++) {
                System.out.print(String.format("%1$3s", (int)A[i][j]));
                System.out.print("\t");
            }
            System.out.println("\t" + (int)B[i]);
            System.out.println();
        }
    }
    private double determinant(double[][] a, int n){
        double determinantValue = 0, sign = 1, counterOne, counterTwo;

        if(n == 1){
            determinantValue = a[0][0];
        }
        else{
            double A_minusOne[][] = new double[n-1][n-1];
            for(int x = 0; x < n; x++){
                counterOne = 0; counterTwo = 0;
                for(int i = 1; i < n; i++)
                    for (int j = 0; j < n; j++)
                        if (j != x) {
                            A_minusOne[(int) counterOne][(int) counterTwo++] = a[i][j];
                            if (counterTwo % (n - 1) == 0) {
                                counterOne++;
                                counterTwo = 0;
                            }
                        }
                determinantValue = determinantValue + a[0][x] * determinant(A_minusOne, n-1) * sign;
                sign = -sign;
            }
        }
        return determinantValue;
    }
    double[] CrammersRule(double[][] A, double[] B) {
        double[] deters = new double[mSize];
        double[] finalAns = new double[mSize];
        double D = determinant(A,mSize);
        double[][] temp = new double[mSize][mSize];
        for (int k = 0; k < mSize; k++) {
            for (int i = 0; i < mSize; i++) System.arraycopy(A[i], 0, temp[i], 0, A[i].length);
            for (int j = 0; j < mSize; j++) temp[j][k] = B[j];
            deters[k] = determinant(temp, mSize);
        }
        for (int i = 0; i < mSize; i++) System.arraycopy(A[i], 0, temp[i], 0, A[i].length);
        for (int i = 0; i < mSize; i++) finalAns[i] = (deters[i] / D);
        return finalAns;
    }

}

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("for 3x3 enter 3, for 4x4 enter 4 and so on...");
        System.out.print("Enter the size of matrix : ");
        int mSize = scan.nextInt();
        if (mSize == 0) System.exit(0);
        double[][] A = new double[mSize][mSize];
        double[] B = new double[mSize];
        Matrix matrix = new Matrix(mSize);
        matrix.printMatrix(A,B);
        for(int i = 0; i < mSize; i++) {
            for(int j = 0; j < mSize; j++) {
                System.out.print("Enter an element for (" + (i+1) + "." + (j+1) + ") in A : ");
                A[i][j] = scan.nextDouble();
            }
        }
        matrix.printMatrix(A,B);
        for (int i = 0; i < mSize; i++) {
            System.out.print("Enter an element for (" + (i+1) + ") in B : ");
            B[i] = scan.nextDouble();
        }
        matrix.printMatrix(A,B);
        double[] FinalAns = matrix.CrammersRule(A,B);
        for (int i = 0; i < mSize; i++) System.out.println("X" + (i + 1) + " Value = " + FinalAns[i]);
    }
}

