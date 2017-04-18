public class Milestone2{

  public static void main(String[] args){

    Picture img = new Picture("/Users/sunnyfeng/Documents/JavaCodes/RGB.png");
    //Picture img = new Picture("/Users/sunnyfeng/Documents/JavaCodes/Flower.jpeg");

    //instructions
    System.out.println("To switch red and blue: 'switch'");
    System.out.println("To count red pixels: 'red'");
    System.out.println("To flip vertically (over the horizontal): 'flip1'");
    System.out.println("To flip horizontally (over the vertical): 'flip2'");
    System.out.println("To flip over diagonal: 'flip3'");
    String n = IO.readString();

    //carry out methods
    if (n.equals("switch")) switchRedBlue(img);
    if (n.equals("red")) System.out.println(countRedPixels(img));
    if (n.equals("flip1")) flipVertical(img);
    if (n.equals("flip2")) flipHorizontal(img);
    if (n.equals("flip3")) flipDiagonal(img);


  }

  //count red pixels - pure red
    public static int countRedPixels(Picture img){
      int counter = 0;
      for (int x = 0; x < img.getWidth(); x++){
        for (int y = 0; y < img.getHeight(); y++){
          Pixel p = img.getPixel(x,y);
            //if red and nothing else, add to counter
            if (p.getGreen() == 0 && p.getBlue() == 0 && p.getRed() == 255){
              counter++;
            }
        }
      }

      return counter;

    }

  //switch pure blue and pure red pixels
    public static void switchRedBlue(Picture img){
      for (int x = 0; x < img.getWidth(); x++){
        for (int y = 0; y < img.getHeight(); y++){
          Pixel p = img.getPixel(x,y);
            //pure red
            if (p.getGreen() == 0 && p.getBlue() == 0 && p.getRed() == 255){
              p.setBlue(255);
              p.setRed(0);
              //pure blue = only blue
            } else if (p.getGreen() == 0 && p.getRed() == 0 && p.getBlue() == 255){
              p.setRed(255);
              p.setBlue(0);
          }
        }
      }

    //  img.show();
      img.write("/Users/sunnyfeng/Documents/JavaCodes/RGB-modified.png");

    }


    //reflect over vertical line
    public static void flipHorizontal(Picture img){
      for (int x = 0; x < img.getWidth()/2; x++){
        for (int y = 0; y < img.getHeight(); y++){
          Pixel p = img.getPixel(x,y);
          Pixel pNew = img.getPixel((img.getWidth()-1-x), y);
          pNew.setBlue(p.getBlue());
          pNew.setRed(p.getRed());
          pNew.setGreen(p.getGreen());
        }
      }

    //  img.show();
      img.write("/Users/sunnyfeng/Documents/JavaCodes/RGB-modified.png");

    }

    //reflect over horizontal line
    public static void flipVertical(Picture img){
        for (int x = 0; x < img.getWidth(); x++){
          for (int y = 0; y < img.getHeight()/2; y++){
            Pixel p = img.getPixel(x,y);
            Pixel pNew = img.getPixel(x, (img.getHeight()-1-y));
            pNew.setBlue(p.getBlue());
            pNew.setRed(p.getRed());
            pNew.setGreen(p.getGreen());
          }
        }

    //  img.show();
      img.write("/Users/sunnyfeng/Documents/JavaCodes/RGB-modified.png");

    }

    // flip over / line
    //assume square
    public static void flipDiagonal(Picture img){
      if (img.getWidth() != img.getHeight()){
        System.out.println("Image not Square");
        return;
      } else {
          int length = img.getWidth();
          for (int x = 0; x < length; x++){
              for (int y = 0; y < length-1-x; y++){
                  Pixel p = img.getPixel(x,y);
                  Pixel pNew = img.getPixel((length-1-y),(length-1-x));
                  pNew.setBlue(p.getBlue());
                  pNew.setRed(p.getRed());
                  pNew.setGreen(p.getGreen());
              }
          }

        //  img.show();
          img.write("/Users/sunnyfeng/Documents/JavaCodes/RGB-modified.png");
      }

    }


}
