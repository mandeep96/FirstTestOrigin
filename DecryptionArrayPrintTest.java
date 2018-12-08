import java.util.ArrayList;
import java.lang.Math;

//This class tests the print array method of Decryption class
public class DecryptionArrayPrintTest
{
  public static void main(String[] args)
  {
    ArrayList testArray = new ArrayList();
    for(int i = 0; i < 200; i++)
    {
      testArray.add((int)(Math.random() * 2));
    }
    String stringToPrint = Decryption.printArray(testArray);
    System.out.println("" + stringToPrint);
  }

}
