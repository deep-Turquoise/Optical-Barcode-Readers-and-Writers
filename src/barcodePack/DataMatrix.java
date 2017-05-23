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
       image = new BarcodeImage();
       text = "undefined";
       actualWidth = 0;
       actualHeight = 0;
   }
   
   public DataMatrix(BarcodeImage giv)
   {
      image = giv;
      scan(giv);
   }

   public DataMatrix(String giv)
   {
       image = new BarcodeImage();
       readText(giv);
   }
   
   public boolean scan(BarcodeImage giv)
   {
      image = (BarcodeImage)giv.clone();
      cleanImage();
      return false;
   }

   public boolean readText(String giv)
   {
      if (giv != null)
      {
         text = giv;
         return true;
      }
      return false;
   }

   public boolean generateImageFromText()
   {
      return false;
   }

   public boolean translateImageToText()
   {
      return false;
   }

   public void displayTextToConsole()
   {
      
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
   
   private void findTopLeftCoords(int xTopLeft, int yTopLeft)
   {
      for(int y = 0; y < image.MAX_HEIGHT; ++y)
      {
         for(int x = 0; x < image.MAX_WIDTH; ++x)
         {
            if(image.getPixel(y, x) == true)
            {
               xTopLeft = x;
               yTopLeft = y;
               break;
            }
         }
      }
   }
   
   private void determineHeight(int xTopLeft, int yTopLeft)
   {
      for(int y = yTopLeft; y < image.MAX_HEIGHT; ++y)
      {
         if(image.getPixel(y, xTopLeft) == true)
         {
            actualHeight = y - yTopLeft;
         }
         else
         {
            break;
         }
      }
   }
   
   private void determineWidth(int xTopLeft, int yTopLeft)
   {
      for(int x = xTopLeft; x < image.MAX_WIDTH; ++x)
      {
         if(image.getPixel(yTopLeft+actualHeight, x) == true)
         {
            actualWidth = x- xTopLeft;
         }
         else
         {
            break;
         }
      }
   }
   
   private void shiftDown(int xTopLeft, int yTopLeft)
   {
      //find bottom y coord
      for(int y = image.MAX_HEIGHT-1; y >= 0; --y)
      {
         for(int x = 0; x < image.MAX_WIDTH; ++x)
         {
            // sw
         }
      }
   }
   
   private void cleanImage()
   {
      int xTopLeft = 0;
      int yTopLeft = 0;
      findTopLeftCoords(xTopLeft, yTopLeft);
      
      determineWidth(xTopLeft, yTopLeft);
      determineHeight(xTopLeft, yTopLeft);
      shiftDown(xTopLeft, yTopLeft);
   }
}
























