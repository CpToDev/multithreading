package assignmentMultithreading;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

public class SumOfFactorialThread extends Thread{
    Stream<BigInteger> stream;

    public SumOfFactorialThread(Stream<BigInteger> stream) {
        this.stream = stream;
    }
    public BigInteger factorial(BigInteger num){
       BigInteger result=BigInteger.ONE;

        for (int i = 1; i <= num.intValue(); i++) {
            result= result.multiply(BigInteger.valueOf(i));
        }
       return result;

    }
    public void calSumOfFactorial() throws IOException {

        FileWriter fw=new FileWriter("sum.txt");
        BigInteger result=stream.map(this::factorial).reduce(BigInteger.ONE,BigInteger::add);

        fw.write(result.toString());
        fw.close();

    }

    @Override
    public void run() {
        try {
            calSumOfFactorial();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }
}
