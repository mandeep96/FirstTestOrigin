import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Encryption
{
  private static int[] array;
  public static void main(String args[])
  {
    String string = args[1];
    String inputFile = args[0];
    stringToBinary(string);
    System.out.println(array.length);
    BufferedImage image = null;
    image = encryptImage(inputFile);
    System.out.println(image.getRGB(0,1));
    try {
    File outputfile = new File("output.png");
    ImageIO.write(image, "png", outputfile);
    } catch (IOException e) {
}
  
  } // main

  private static void stringToBinary(String string) {    
    array = new int[string.length()*8];
    String byteString;
    int currentElement = 0;
    for(int i=0; i < string.length(); i++) {
      byteString = Integer.toBinaryString(string.charAt(i));
      int length = byteString.length();
      for(int j = 0; j < 8 - length; j++)
        byteString = "0" + byteString;
      for(int k = 0; k < 8; k++)
      {
        if (byteString.charAt(k) == '1')
          array[currentElement] = 1;
        else 
          array[currentElement] = 0;
        currentElement ++;
      } // for
    
    } // for
  } // stringToBinary

  private static BufferedImage encryptImage(String file) {
  BufferedImage image = null;
    try {
      image = ImageIO.read(new File(file));
    } catch (IOException e) {
    }

  try {
      image = ImageIO.read(new File(file));
  } catch (IOException e) {
    }
  int width = image.getWidth();
  int height = image.getHeight();
  int top = (int) (height*0.05);
  int bottom = (int) (height*0.95);
  int currentElement = 0;
  for (int r = 0; r < top; r++)
  {
    for (int c = 0; c < width - width%8; c ++)
    {     
        if (currentElement < array.length)
        {
          if (array[currentElement] == 1)
            image.setRGB(c, r, -16777215);
          else if(array[currentElement] == 0)
            image.setRGB(c, r, -16777214);
          else
            image.setRGB(c, r, -1);
          currentElement ++;
        } // if
        else
          image.setRGB(c, r, -16777213);
       if (c != width - width%8 - 1)
        for (int i = width - width%8; i < width; i++)
          image.setRGB(i, r, -16777213);

    } // for
  } // for

  for (int r = bottom; r < height; r++)
  {
    for (int c = 0; c < width; c ++)
    {
      if (currentElement < array.length)
        {
          if (array[currentElement] == 1)
            image.setRGB(c, r, -16777215);
          else if(array[currentElement] == 0)
            image.setRGB(c, r, -16777214);
          else
            image.setRGB(c, r, -1);
          currentElement ++;
        } // if
        else
          image.setRGB(c, r, -16777213);
       if (c != width - width%8 - 1)
        for (int i = width - width%8; i < width; i++)
          image.setRGB(i, r, -16777213);

    } // for
  } // for
  /*  BufferedImage image = null;
    try {
      image = ImageIO.read(new File(args[0]));
    } catch (IOException e) {
    }
    int width = image.getWidth();
    System.out.println(image.getRGB(0,0));
    image.setRGB(0,0,-16777216);
    System.out.println(image.getRGB(0,0));

    try {
    // retrieve image
    File outputfile = new File("output.png");
    ImageIO.write(image, "png", outputfile);
} catch (IOException e) {
}*/
  return image;

  } // encrypImage



} // Encryption
