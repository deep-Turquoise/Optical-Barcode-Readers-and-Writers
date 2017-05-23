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
       actualWidth = 0;
       actualHeight = 0;
       text = "undefined";
   }
   
   public DataMatrix(BarcodeImage bc)
   {
      image = bc;
      // TO-DO:  Call scan() and avoid duplication of code here.
   }

   public DataMatrix(String giv)
   {
       this();
       
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
