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
   
   public BarcodeImage(String[] sImageIn)
   {
      // Initialize the array to all 'false'
      this(); // this calls the other method, so the array will all be false.
      
      // get dimensions of array.
      int height = sImageIn.length;
      int width  = sImageIn[0].length();
      
      int top = 0;
      int left = 0;  
      
      // get the top left corner of the image.
      for (int i = 0; i < height; i++)
      {
         for (int k = 0; k < width; k++)
         {
            if (sImageIn[i].charAt(k) == BLACK_CHAR)
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
            if (sImageIn[i + top].charAt(k + left) == BLACK_CHAR)
            {
               image_data[i+20][k] = true;
            }
         }
      }
      int test;
       test = 1;
   }
   
   public void doSomething(BarcodeIO foo)
   {
      foo.readText(" ");
   }
   
   public boolean getPixel(int row, int col)
   {
      if(col <= MAX_HEIGHT && row <= MAX_WIDTH)
      {
         if(image_data[col][row] == true) { return true; }
         else { return false; }
      }
      else { return false; }
   }
   
   public boolean setPixel(int row, int col, boolean value)
   {
      if(col <= MAX_HEIGHT && row <= MAX_WIDTH)
      {
         image_data[col][row] = value;
         return true;
      }
      else { return false; }
   }
}
