package barcodePack;

public class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data;

   // Default constructor for BarcodeImage.
   public BarcodeImage()
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            image_data[i][j] = false;
         }
      }
   }

   // Constructor for BarcodeImage that takes an array of Strings.
   public BarcodeImage(String[] str_data)
   {
      this(); // start by creating new empty array
      
      if (checkSize(str_data))
      {
         for (int i = 0; i < str_data.length; i++)
         {
            for (int j = 0; j < str_data[0].length(); j++)
            {
               if (str_data[i].charAt(j) == '*')
               {
                  image_data[MAX_HEIGHT - str_data.length + i][j] = true;
               }  
            }
         }
      } 
      
      else
      {
         System.out.println("Data is not the right size");
      }

   }

   // simple accessor for a pixel at a given location
   public boolean getPixel(int row, int col)
   {
      return image_data[row][col];
   }

   // simple mutator for a pixel at a given location.
   public boolean setPixel(int row, int col, boolean value)
   {
      if (row < MAX_HEIGHT && col < MAX_WIDTH)
      {
         image_data[row][col] = value;
         return true;
      }
      return false;
   }
   
   // helper method to make sure incoming data isn't too large 
   private boolean checkSize(String[] data) // Optional Method
   {
      if (data.length < MAX_HEIGHT && data[0].length() < MAX_WIDTH)
      {
         return true;
      }
      return false;
   }

   // helper method to display current contents of "image structure"
   public void displayToConsole() // Optional Method
   {
      System.out.println("---------x---------x---------x---------x---------x---------x");
      for (int i = 0; i < MAX_HEIGHT; i++ )
      {
         System.out.print("|");
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            if (image_data[i][j] == true)
            {
               System.out.print('*');
            }
            else
            {
               System.out.print(" ");
            }  
         }
         System.out.println();
      }
      System.out.println("---------x---------x---------x---------x---------x---------x");
   }
   
   // this satisfies the "implements Cloneable" and creates and returns a copy of the called 
   // instance variable
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      BarcodeImage newImage = (BarcodeImage)super.clone();
      boolean thisPixel;
      
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         for (int k = 0; k <MAX_WIDTH; k++)
         {
            thisPixel = image_data[i][k];
            newImage.setPixel(i, k, thisPixel);
         }
      }
      return newImage;
   }
   
}
