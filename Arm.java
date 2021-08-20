import java.util.ArrayList;
import java.util.TreeSet;

public class Arm {
    public static long[][] multiTable;
    private static TreeSet<Long> list = new TreeSet<>();
    private static ArrayList<Long> listForLong = new ArrayList<>();
    public static long[] getNumbers(long N)  {

        createMultiTable(N); // создаем двухмерный массив

        long[] result = null;
            int degree2 = numbersLong(N);
                if (N >1) {
                for (long i = 1; i <= N; ) {
                    for (int j = numbersLong(i); j <= degree2; j++) { // проверяем числа в разных степенях
                        long x = sumOfNum(i, j);
                        if (x < N && isArmstrongNum(x) ) {
                            list.add(x);
                        }
                    }
                    i = unicumNumber(i);
                    if (i < 0) {
                        break;
                    }
                }
                for (long x : list) {
                listForLong.add(x);
            }
                result = new long[listForLong.size()];
            for (int i = 0; i < listForLong.size(); i++) {
            result[i]=listForLong.get(i);
                System.out.println(result[i] + "  ");
            }
        }
          //  System.out.println(result.length );
         return result;
    }
    // создание массива 10 на количество степеней числа N
    private static void createMultiTable(long n) {
        int degree = numbersLong(n)+1;
        multiTable = new long[10][degree];
        for (int i = 0; i < multiTable.length ; i++) {
            long elementTable=1;
            for (int j = 0; j < multiTable[i].length ; j++) {
                multiTable[i][j]=elementTable;
                elementTable *= i;
            }
        }
    }
    // считаем степенную сумму
    public static long sumOfNum(long n, int ... degree) {
        long summa2 = 0;
        int size;
        try {
            size= degree[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            size = numbersLong(n);
        }

        int[] someNumbers = new int[size];
        long[] sum = new long[size];
        for (int j = 0; j < someNumbers.length ; j++) {
            someNumbers[j]= (int) (n%10);
            n /=10;
        }

        for (int i = 0; i < size; i++) {
            sum[i] = multiTable[someNumbers[i]][size];
            summa2+=sum[i];
            if (summa2<0) {
                summa2=0;
            }
        }

        return summa2;
    }
    // вычисляем количество чисел в числе
    public static int numbersLong(long x) {
        long p = 10;
        for (int i=1; i<19; i++) {
            if (x < p)
                return i;
            p = 10*p;
        }
        return 19;
    }
    public static boolean isArmstrongNum(long num ) {
        if (sumOfNum(num)==num) {
            return true;
        }
        else {
            return false;
        }
    }
    // формируем следующее число для проверки если 55 то след 56
//если 99 то след 111
//если 1299 то след 1333
// числа с нулями проверяются выше через степени
    private static long unicumNumber(long i) {
        long nextNumber=i;
        int size =  numbersLong(i);
        byte[] cerrentNum = new byte[size];
        int x = 0;

        for (int j = size-1; j >=0 ; j--) {
            cerrentNum[j] = (byte) (i%10);
            i/=10;
        }
        if (cerrentNum[size-1]==9) {
            for (int j = size - 1; j >= 1; j--) {
                if (cerrentNum[j] == 9) {
                    x++;
                }
            }
            if (cerrentNum[0]==9 && cerrentNum[size-1]==9) {
                nextNumber = 1+nextNumber + (nextNumber/9);
            }
            else {
                int c = cerrentNum[size-x-1]+1;
                long a=0;
                for (int j = 0; j < x ; j++) {
                   // long b = (long) (Math.pow(10,j)*c);
                    long b = 1;
                    long d;
                    for (int k = 1; k <=j ; k++) {
                        b = b*10;
                    }
                    d=b*c;
                    a= a+d;
                }
                nextNumber = nextNumber +a +1;
            }

        }
        else nextNumber +=1;
        return nextNumber;
    }
    public static void main(String[] args) {
        listForLong.clear();
        list.clear();
        long start = System.currentTimeMillis();
        getNumbers(Long.MAX_VALUE);
      //  System.out.println();

        System.out.println(System.currentTimeMillis()-start + "  ms");


    }
}