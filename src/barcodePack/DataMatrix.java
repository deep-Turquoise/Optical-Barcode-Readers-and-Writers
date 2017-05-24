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
   
   public boolean readText(String newText)
   {
      if (newText != null)
      {
         text = newText;
         return true;
      }
      return false;
   }
   
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
   
   public int getActualWidth()
   {
      return actualWidth;
   }
   
   public int getActualHeight()
   {
      return actualHeight;
   }
   
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
   private void clearImage()
   {
      image = new BarcodeImage();
   }
   
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

   public boolean generateImageFromText()
   {
      clearImage();
      System.out.println("\n\n\nHERE --------------------------------------------------------------------------\n\n\n");
      System.out.println(text);
      
      int width = text.length();
      int height = 10; 
      createStarFrame(height, width);
      
      for(int x = 0; x < text.length(); ++x)
      {
         int ascii = text.charAt(x);
         WriteCharToCol(x+1, ascii);
      }
      
      System.out.println("\n\n\nEND --------------------------------------------------------------------------\n\n\n");
      return false;
   }

   @Override
   public boolean translateImageToText()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public void displayTextToConsole()
   {
      System.out.println(text);
   }
}
























