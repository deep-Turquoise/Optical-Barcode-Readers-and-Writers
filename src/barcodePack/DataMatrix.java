package barcodePack;
import barcodePack.BarcodeIO;

public class DataMatrix implements BarcodeIO
{
   public DataMatrix()
   {
      
   }
   
   public DataMatrix(BarcodeImage bc)
   {
      
   }
   
   public boolean scan(BarcodeImage bc)
   {
      return false;
   }

   public boolean readText(String text)
   {
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
      
   }
}