package barcodePack;

public class BarcodeImage
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data;
   
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
    for (int i = 0; i < sImageIn.length; i++)
    {
       for (int k = 0; k < sImageIn[0].length(); k++)
       {
          
       }
    }
   }
   
   public void doSomething(BarcodeIO foo0)
   {
      
   }
}
