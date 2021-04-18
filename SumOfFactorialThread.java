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
    
    /*This is highly inefficient, if you are calculating factorial of all the numbers in the same thread then why not store the previous number's result ?
    Either calulcate the results in different threads to achieve parallelism or if doing in same threads then use DP (store the previous number's result)
    */
    public BigInteger factorial(BigInteger num){
       BigInteger result=BigInteger.ONE;

        for (int i = 1; i <= num.intValue(); i++) {
            result= result.multiply(BigInteger.valueOf(i));
        }
       return result;

    }
    public void calSumOfFactorial() throws IOException {

        FileWriter fw=new FileWriter("sum.txt");
        BigInteger result=stream.map(this::factorial).reduce(BigInteger.ONE,BigInteger::add); /* This in incorrect, if you are adding in the reduce function, 
        you should zero as the identity not one, it's not caluclating product
        That is why the answer is incorrect, if you nos. 1, 2, 3, 4 in your stream the answer is coming out to 34 instead of 33
        
        You should parallel streams for this operation, it will enhance the performance
        */
        
        // use the below code instead
        BigInteger result=stream.parallel().map(this::factorial).reduce(BigInteger.ONE,BigInteger::add);
        

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
