package assignmentMultithreading;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

public class PrimeProductThread extends Thread{
    Stream<BigInteger>stream;

    public PrimeProductThread(Stream<BigInteger> stream) {
        this.stream = stream;
    }
    public boolean isPrime(BigInteger num){

        int n=num.intValue();
        for(int i=2;i<n;i++){
            if(n%i==0)
                return false;
        }
        return true;
    }
    public void calPrimeProduct() throws IOException {

        FileWriter fw=new FileWriter("prime.txt");
        BigInteger result=stream.filter(this::isPrime).reduce(BigInteger.ONE,BigInteger::multiply);
        fw.write(result.toString());
        fw.close();
    }

    @Override
    public void run() {
        try {
            calPrimeProduct();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
