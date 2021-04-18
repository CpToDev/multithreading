package assignmentMultithreading;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Stream;

public class ProductThread extends Thread{

    Stream stream;

    public ProductThread(Stream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        try {
            calProduct();
        } catch (IOException e) {
            System.out.println("Error calculating product");
        }

    }
    public  void calProduct() throws IOException {

        FileWriter fw = new FileWriter("product.txt");

        BigInteger result= (BigInteger) stream.reduce(BigInteger.ONE,(i, j)->{

            return ((BigInteger) i).multiply((BigInteger) j);
        });
        fw.write(result.toString());
        fw.close();
    }
}
