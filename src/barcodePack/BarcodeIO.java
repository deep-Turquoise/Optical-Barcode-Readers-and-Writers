package barcodePack;
import barcodePack.BarcodeImage;

public interface BarcodeIO
{
   public abstract boolean scan(BarcodeImage bc);

   public abstract boolean readText(String text);

   public abstract boolean generateImageFromText();

   public abstract boolean translateImageToText();

   public abstract void displayTextToConsole();

   public abstract void displayImageToConsole();
}
