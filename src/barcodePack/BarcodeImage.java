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

      for(int y = 0; y < str_data.length; ++y)
      {
         for(int x = 0; x < str_data[y].length(); ++x)
         {
            if(str_data[y].charAt(x) == BLACK_CHAR)
            {
               image_data[y][x] = true;
            }
         }
      }


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

   private void checkSize(String[] data)
   {

   }

   public void displayToConsole()
   {
      System.out.println("");
   }
}
