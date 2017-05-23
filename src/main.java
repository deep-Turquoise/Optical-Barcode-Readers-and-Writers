// Nicholas Nelson, David Henderson, Christopher Calderon
// Nathan Mauga, Haley Dimapilis
// CST338-30_SU17: Software Design
// Module 4
// (M4) Write a Java program (Optical Barcode Readers) (4 hrs)

import barcodePack.*;

public class main
{
   public static void main(String[] args)
   {
      String[] sImageIn =
      {
         "                                               ",
         "                                               ",
         "                                               ",
         "     * * * * * * * * * * * * * * * * * * * * * ",
         "     *                                       * ",
         "     ****** **** ****** ******* ** *** *****   ",
         "     *     *    ****************************** ",
         "     * **    * *        **  *    * * *   *     ",
         "     *   *    *  *****    *   * *   *  **  *** ",
         "     *  **     * *** **   **  *    **  ***  *  ",
         "     ***  * **   **  *   ****    *  *  ** * ** ",
         "     *****  ***  *  * *   ** ** **  *   * *    ",
         "     ***************************************** ",  
         "                                               ",
         "                                               ",
         "                                               "

      };      
      //---------------------------------------------------------------------------------------------------------------      
      BarcodeImage myBarcodeImage = new BarcodeImage(sImageIn);
      myBarcodeImage.displayToConsole(); //This is just for debugging
      
      DataMatrix myDM = new DataMatrix(myBarcodeImage); // Took in above barcode
      
      myDM.displayImageToConsole(); //Trimmed and bordered per instructions
      
      
      System.out.println(myDM.getActualHeight()); //Verifying height which was found by using computeSignalHeight
      String myString = "*****************************************"; //Copied this from above to verify length
      //Verifying width which was found by using computeSignalWidth
      System.out.println("String:" + myString.length() + " vs getActualWidth:" + myDM.getActualWidth());
      //--------------------------------------------------------------------------------------------------------------   
      
      String[] sImageIn_2 =
      {
            "                                          ",
            "                                          ",
            "* * * * * * * * * * * * * * * * * * *     ",
            "*                                    *    ",
            "**** *** **   ***** ****   *********      ",
            "* ************ ************ **********    ",
            "** *      *    *  * * *         * *       ",
            "***   *  *           * **    *      **    ",
            "* ** * *  *   * * * **  *   ***   ***     ",
            "* *           **    *****  *   **   **    ",
            "****  *  * *  * **  ** *   ** *  * *      ",
            "**************************************    ",
            "                                          ",
            "                                          ",
            "                                          ",
            "                                          "

      };
      System.out.println(sImageIn.length);
      System.out.println(sImageIn[0].length());
      System.out.println(sImageIn[0]);
      
      System.out.println(sImageIn[5]);
      System.out.println(sImageIn_2);

      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);
     
      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      // create your own message
      dm.readText("What a great resume builder this is!");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();

   }   
}
