package assignmentMultithreading;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Task {

    public static void main(String[] args) throws IOException {
        Random r= new Random();

        Supplier<Stream<BigInteger>> supplier= () ->r.ints(200,10000,20000).mapToObj(i->BigInteger.valueOf(i));

        Thread t1=new ProductThread(supplier.get());
        Thread t2=new SumOfFactorialThread(supplier.get());
        Thread t3=new PrimeProductThread(supplier.get());
        t1.start();
        t2.start();
        t3.start();

    }


}

