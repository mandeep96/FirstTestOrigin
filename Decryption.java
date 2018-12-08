import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.lang.Math;
import java.io.IOException;
/*
 * This class will take an image with a hidden message and print it on 
 * standard output.
*/
public class Decryption
{

  public static void main(String[] args)
  {
      BufferedImage image = null;
      try {
        image = ImageIO.read(new File(args[0]));
      } catch (IOException exception) {
        System.err.println("Something went wrong reading from the file" 
                           + exception.getMessage());
      }//End tring to open the image

      //Declaring variables needed
      int imageHeight = image.getHeight();
      int imageWidth = image.getWidth();
  
      //Passing variables and images to decryption method
      ArrayList binaryArray = decryption(imageHeight, imageWidth, image);
      System.out.println(binaryArray.size());

      //Array passed to method to print array
      String message = printArray(binaryArray);
      //Printing the string returned
      System.out.println(message);
    
  }//End main method

  /*
   *This method will decrypt the image and retrun a string of the message 
   *It takes height and width of the image then a buffered image
  */
  public static ArrayList decryption(int height, int width, BufferedImage image)
  {
    //Declaring all the variables we would need
    ArrayList binaryArray = new ArrayList();
    int pixelsTop = (int)(height * 0.05);
    int pixelsBottom = (int)(height - (height * 0.05));
    int k = 0;

    //This loop will go through the height from the top to read from
    for(int i = 0; i < pixelsTop;i++)
    {
      //This for loop will loop along each row of pixels and assign 1 or 0 
      //depending on the value of the pixel
      for(int j = 0; j < width - (width % 8); j++)
      {
        if(image.getRGB(j,i) == -16777215) {
          binaryArray.add(k,1);
          k++;
        }
        else if(image.getRGB(j,i) == -16777214) {
          binaryArray.add(k,0);
          k++;
        }
        else if(image.getRGB(j,i) == -16777213)
          j = width;
        else {
          binaryArray.add(k,2);
          k++;
        }
        
      }//End inner for
    }//End outer for

    //This loop will go through the height from the bottom to read from
    for(int i = pixelsBottom; i < height;i++)
    {
      //This for loop will loop along each row of pixels and assign 1 or 0 
      //depending on the value of the pixel
      for(int j = 0; j < width - (width % 8); j++)
      {
        if(image.getRGB(j,i) == -16777215)
          binaryArray.add(k,0);
        else if(image.getRGB(j,i) == -16777214)
          binaryArray.add(k,1);
        else if(image.getRGB(j,i) == -16777213)
          j = width;
        else
          binaryArray.add(k,2);
        k++;
        //Checking to see if we are at the end of the text
        
      }//End inner for
    }//End outer for

    return binaryArray;
  }//End decryption method

  /*
   *This method will convert the binary array into a string of the related
   *characters
  */
  public static String printArray(ArrayList arrayToPrint)
  {
    //Declaring variables to use in the loop
    double currentBitValue;
    int result;
    String string = "";

    //This will loop through the whole array, 8 indicies at a time
    for(int index = 0; index < arrayToPrint.size(); index+=8)
    {
      result = 0;
      //This for loop goes through each of the 8 indicies for each char
      for(int i = 0; i < 8; i++)
      {
        if(index + i >= arrayToPrint.size())
          break;
        currentBitValue = 128 / (Math.pow(2,i));
        //This checks to see if the value is 1
        if((int)arrayToPrint.get(index + i) == 1)
          result += currentBitValue;
        else if((int)arrayToPrint.get(index + i) == 0);
        //If something goes wrong store -1 in result
        else
        {
          result = -1;
          break;
        }
      }//End inner for

      string = string + "" + (char)result;
    }//End outer for
    return string;

  }//End printArray method

}//End class Decryption
