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
   
   public DataMatrix()
   {
      text = "undefined";
      image = new BarcodeImage();
      actualWidth = 0;
      actualHeight = 0;
   }
   
   public DataMatrix(BarcodeImage image)
   {
      this.image = image;
      scan(image); 
   }
   
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

   @Override
   public boolean generateImageFromText()
   {
      // TODO Auto-generated method stub
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
























