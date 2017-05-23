package barcodePack;

public class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data;

   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';

   public BarcodeImage()
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         for (int k = 0; k < MAX_WIDTH; k++ )
            image_data[i][k] = false;
      }
   }

   public BarcodeImage(String[] str_data)
   {
      // Initialize the array to all 'false'
      this(); // this calls the other method, so the array will all be false.

      if(checkSize(str_data) == true)
      {
         //image may be smaller so pack into lower left hand corner
         for(int y = MAX_HEIGHT-1; y >= 0; --y)
         {
            for(int x = 0; x < MAX_WIDTH; ++x)
            {
               if(x < str_data[0].length() && (str_data.length-(MAX_HEIGHT-y)) >= 0)
               {
                  if(str_data[str_data.length-(MAX_HEIGHT-y)].charAt(x) == BLACK_CHAR)
                  {
                     image_data[y][x] = true;
                  }
               }
            }
         }
      }
      displayToConsole();

      /* David's Code
       *  
       // Initialize the array to all 'false'
      this(); // this calls the other method, so the array will all be false.

      // get dimensions of array.
      int height = str_data.length;
      int width  = str_data[0].length();

      int top = 0;
      int left = 0;  

      // get the top left corner of the image.
      for (int i = 0; i < height; i++)
      {
         for (int k = 0; k < width; k++)
         {
            if (str_data[i].charAt(k) == BLACK_CHAR)
            {
               top = k;
               left = i;
               break; // end this loop we have what we want.
            }
         }
      }

      // iterate and set the boolean array
      for (int i = 0; i < 10; i++)
      {
         for (int k = 0; k < width - left ; k++ )
         {
            if (str_data[i + top].charAt(k + left) == BLACK_CHAR)
            {
               image_data[i+20][k] = true;
            }
         }
      }
      int test;
       test = 1;
       * 
       */
   }


   public void doSomething(BarcodeIO foo)
   {
      foo.readText(" ");
   }

   public boolean getPixel(int row, int col)
   {
      if(col <= MAX_HEIGHT && row <= MAX_WIDTH)
      {
         if(image_data[row][col] == true) { return true; }
         else 
         { 
            return false; 
         }
      }
      else 
      { 
         return false; 
      }
   }

   public boolean setPixel(int row, int col, boolean value)
   {
      if(col <= MAX_HEIGHT && row <= MAX_WIDTH)
      {
         image_data[row][col] = value;
         return true;
      }
      else 
      { 
         return false; 
      }
   }

   private boolean checkSize(String[] data)
   {
      int height = data.length;
      int width = data[0].length();
      if(height < MAX_HEIGHT || width < MAX_WIDTH)
      {
         return true;
      }
      else
      {
         System.out.println("The input image size is too large");
         return false;
      }
   }

   public void displayToConsole()
   {
      System.out.println("Beginning of output");
      for(int y = 0; y < MAX_HEIGHT; ++y)
      {
         for(int x = 0; x < MAX_WIDTH; ++x)
         {
            if(image_data[y][x] == true)
            {
               System.out.print("*");
            }
            else
            {
               System.out.print("_");
            }
         }
         System.out.println();
      }
      System.out.println("End of output");
   }
   
   public Object clone()
   {
      try
      {
         BarcodeImage copy = (BarcodeImage)super.clone();
         copy.image_data = image_data.clone();
         return copy;
      }
      catch(CloneNotSupportedException e)
      {
         System.out.println("Cloning Failed");
         return null;
      }
   }
}
