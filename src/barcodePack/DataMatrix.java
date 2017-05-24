package barcodePack;
import barcodePack.BarcodeIO;

public class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   // Default constructor.
   public DataMatrix()
   {
      text = "undefined";
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
   }
   
   // Constructor that takes a BarcodeImage object as an argument and uses it in the 
   public DataMatrix(BarcodeImage image)
   {
      scan(image); // this will store a clone of the image in the object
   }
   
   // Constructor that takes a String, this is for turning a string to image.
   public DataMatrix(String newText)
   {
      if (readText(newText) == true)
      {
         text = newText;
      }
   }
   
   // simple mutator for private text variable
   public boolean readText(String newText)
   {
      if (newText != null)
      {
         text = newText;
         return true;
      }
      return false;
   }
   
   // this method makes a duplicate of a BarcodeImage given as an argument
   // it stores the copy as the internal private variable for self, then
   // performs some cleanup.
   public boolean scan(BarcodeImage image)
   {
      if (image != null)
      {
         try
         {
            this.image = image.clone();
         }
         catch(CloneNotSupportedException e)
         {
            
         }
         cleanImage();
         actualHeight = computeSignalHeight();
         actualWidth = computeSignalWidth();
         return true;
      }
      return false;
   }
   
   // simple accessor for the actualWidth private variable
   public int getActualWidth()
   {
      return actualWidth;
   }
   
   // simple accessor for th eactualHeight private variable
   public int getActualHeight()
   {
      return actualHeight;
   }
   
   // this private method computes the width of a 'signal' from
   // the BarcodeImage object.
   private int computeSignalWidth()
   {
      for (int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
         if (image.getPixel(BarcodeImage.MAX_HEIGHT - 1, i) == false)
         {
            return i;
         }
      }
      return BarcodeImage.MAX_WIDTH;
   }
   
   // this private method computes the height of a 'signal' from
   // the BarcodeImage object.
   private int computeSignalHeight()
   {
      for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         if (image.getPixel(i, 0) == true)
         {
            return BarcodeImage.MAX_HEIGHT - i;
         }
      }
      return BarcodeImage.MAX_HEIGHT;
   }
   
   // this method 'cleans' the BarcodeImage object by putting the 'signal'
   // into the lower left corner of its internal array.
   private void cleanImage()
   {
      BarcodeImage newImage = new BarcodeImage();
      
      for (int i = locateTopLeftRow(image), y = 0; i < BarcodeImage.MAX_HEIGHT; i++, y++)
      {
         for (int j = locateTopLeftCol(image), x = 0; j < BarcodeImage.MAX_WIDTH; j++, x++)
         {
            newImage.setPixel(20 + y, x, image.getPixel(i, j));
         }
      }
      image = newImage;
   }
   
   // this method finds the top left of the image data in the BarcodeImage
   // structure
   private int locateTopLeftRow(BarcodeImage image) //Helper Method
   {
      for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if (image.getPixel(i, j))
            {
               return i;
            }
         }
      }
      return -1;
   }
   
   // this method finds the top left column of the image data
   // in the barcode image.
   private int locateTopLeftCol(BarcodeImage image) //Helper Method
   {
      for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if (image.getPixel(i,j))
            {
               return j;
            }
         }
      }
      return -1;
   }
   
   // This method outputs a representation of the image as it appears in the
   // BarcodeImage structure.
   public void displayImageToConsole()
   {
      for (int i = 0; i < actualWidth + 2; i++)
      {
         System.out.print("-");
      }
      
      System.out.println();
      
      for (int i = 20; i < BarcodeImage.MAX_HEIGHT; i++ )
      {
         System.out.print("|");
         for (int j = 0; j < actualWidth; j++)
         {
            if (image.getPixel(i, j) == true)
            {
               System.out.print(BLACK_CHAR);
            }
            else
            {
               System.out.print(WHITE_CHAR);
            }  
         }
         System.out.println("|");
      }
   }
   
   // this method resets the BarcodeImage object with a new one.
   private void clearImage()
   {
      image = new BarcodeImage();
   }
   
   // This method creates the outline for a barcode symbol
   // within the BarcodeImage object.
   private void createStarFrame(int height, int width)
   {
      int inImageHeight = BarcodeImage.MAX_HEIGHT - height - 1;
      for(int y = BarcodeImage.MAX_HEIGHT; y > 0; --y)
      {
         for(int x = 0; x <= width+1; ++x)
         {
            //left bar
            if(y > inImageHeight && x == 0)
            {
               image.setPixel(y, x, true);
            }
            
            //bottom bar
            if(y == BarcodeImage.MAX_HEIGHT-1 && x < width+1)
            {
               image.setPixel(y, x, true);
            }
            
            //top bar
            if(y == inImageHeight+1 && x < width && x % 2 != 0)
            {
               image.setPixel(y, x+1, true);
            }
            
            //right bar
            if(x == width+1 && y%2 != 0)
            {
               image.setPixel(y, x, true);
            }
         }
      }
   }
   
   // This helper method writes a character argument to a column.
   private boolean WriteCharToCol(int col, int code)
   {
      while(code > 0)
      {
         if(code >= 128)
         {
            code = code-128;
            image.setPixel(BarcodeImage.MAX_HEIGHT-9, col, true);
         }
         if(code >= 64)
         {
            code = code-64;
            image.setPixel(BarcodeImage.MAX_HEIGHT-8, col, true);
         }
         if(code >= 32)
         {
            code = code-32;
            image.setPixel(BarcodeImage.MAX_HEIGHT-7, col, true);
         }
         if(code >= 16)
         {
            code = code-16;
            image.setPixel(BarcodeImage.MAX_HEIGHT-6, col, true);
         }
         if(code >= 8)
         {
            code = code-8;
            image.setPixel(BarcodeImage.MAX_HEIGHT-5, col, true);
         }
         if(code >= 4)
         {
            code = code-4;
            image.setPixel(BarcodeImage.MAX_HEIGHT-4, col, true);
         }
         if(code >= 2)
         {
            code = code-2;
            image.setPixel(BarcodeImage.MAX_HEIGHT-3, col, true);
         }
         if(code >= 1)
         {
            code = code-1;
            image.setPixel(BarcodeImage.MAX_HEIGHT-2, col, true);
         }
      }
      return false;
   }
   

   // this method creates a barcode from the private text variable.
   public boolean generateImageFromText()
   {
      clearImage();
      
      int width = text.length();
      int height = 10; 
      createStarFrame(height, width);
      
      for(int x = 0; x < text.length(); ++x)
      {
         int ascii = text.charAt(x);
         WriteCharToCol(x+1, ascii);
      }
      actualWidth = computeSignalWidth();
      return false;
   }
   
   // this method sets the private text variable
   // based on BarcodeImage data
   public boolean translateImageToText()
   {
      int ascii = 0;
      String newText = "";
      
      if (image != null)
      {
         for (int i = 1; i < actualWidth - 1; i++)
         {
            for (int j = BarcodeImage.MAX_HEIGHT - actualHeight + 1; j < BarcodeImage.MAX_HEIGHT; j++)
            {
               if (image.getPixel(j, i) == true)
               {
                  if (j == 21)
                  {
                     ascii += 128;
                  }
                  else if (j == 22)
                  {
                     ascii += 64;
                  }
                  else if (j == 23)
                  {
                     ascii += 32;
                  }
                  else if (j == 24)
                  {
                     ascii += 16;
                  }
                  else if (j == 25)
                  {
                     ascii += 8;
                  }
                  else if (j == 26)
                  {
                     ascii += 4;
                  }
                  else if (j == 27)
                  {
                     ascii += 2;
                  }
                  else if (j == 28)
                  {
                     ascii += 1;
                  }
                     
               }
            }
            newText += (char)ascii;
            ascii = 0;
         }
         text = newText;
         return true;
      }
      return false;
   }

   // This method displays the contents of the private text variable.
   public void displayTextToConsole()
   {
      System.out.println(text);
   }
}
























