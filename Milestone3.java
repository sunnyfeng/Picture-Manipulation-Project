public class Milestone3{

  public static void main(String[] args){

    Picture green = new Picture("/Users/sunnyfeng/Documents/JavaCodes/elephantgreenscreen.jpg");
    Picture background = new Picture("/Users/sunnyfeng/Documents/JavaCodes/cityroad.jpg");
    Picture green1 = new Picture("/Users/sunnyfeng/Documents/JavaCodes/pikachugreenscreen.jpg");
    Picture background1 = new Picture("/Users/sunnyfeng/Documents/JavaCodes/grassbackground.jpg");
    Picture green2 = new Picture("/Users/sunnyfeng/Documents/JavaCodes/grayscale.jpg");
    Picture background2 = new Picture("/Users/sunnyfeng/Documents/JavaCodes/sunsetfield.jpg");

    Picture circle = new Picture("/Users/sunnyfeng/Documents/JavaCodes/circlewhitehole.jpg");
    Picture pine = new Picture("/Users/sunnyfeng/Documents/JavaCodes/pineapple2white.jpg");

    //testing methods

    greenScreen(green,background); //elephant on city road
    greenScreen(green1,background1); //pikachu in field
    //greenScreen(green2,background2); //testing to make sure most grays does not count

    objectDetection(circle); //detect donut edges
    objectDetection(pine); //detect pineapple object

  }


  //replace green screen with background
    public static void greenScreen(Picture green, Picture back){

      //background must be the same size or larger than green screen picture
      if (green.getWidth() > back.getWidth() || green.getHeight() > back.getHeight()){
        System.out.println("Background smaller than the picture!");
        return;
      }

      for (int x = 0; x < green.getWidth(); x++){
        for (int y = 0; y < green.getHeight(); y++){
            Pixel g = green.getPixel(x,y);
            Pixel b = back.getPixel(x,y);

            //if mostly green
            if (g.getGreen() > 110 && g.getBlue() < 100 && g.getRed() < 100){
              //switch green pixel to the background pixel
              g.setBlue(b.getBlue());
              g.setRed(b.getRed());
              g.setGreen(b.getGreen());
          }
        }
      }

      green.show();
      green.write("/Users/sunnyfeng/Documents/JavaCodes/elephant-modified.jpg");

  }

  //detect object in front of white background
  public static void objectDetection(Picture pic){

    for (int x = 1; x < pic.getWidth()-1; x++){
      for (int y = 1; y < pic.getHeight()-1; y++){

          Pixel p = pic.getPixel(x,y);
          Pixel pRight = pic.getPixel(x+1,y);
          Pixel pLeft = pic.getPixel(x-1,y);
          Pixel pUp = pic.getPixel(x,y+1);
          Pixel pDown = pic.getPixel(x,y-1);

          //boolean values seeing if pixels around pixel p are white
          boolean isWhite = p.getRed() > 200 && p.getBlue() > 200 && p.getGreen() > 200;
          boolean isWhiteRight = pRight.getRed() > 200 && pRight.getBlue() >200 && pRight.getGreen() >200;
          boolean isWhiteLeft = pLeft.getRed() > 200 && pLeft.getBlue() >200 && pLeft.getGreen() >200;
          boolean isWhiteUp = pUp.getRed() > 200 && pUp.getBlue() >200 && pUp.getGreen() > 200;
          boolean isWhiteDown = pDown.getRed() > 200 && pDown.getBlue() >200 && pDown.getGreen() >200;

        //if the pixel is not white, then it could be an edge or inside object
        if (!isWhite){
          //if there is a white right edge and the left isn't white, is an edge
          if (isWhiteRight && !isWhiteLeft){
              //make a red border
              p.setBlue(0);
              p.setRed(255);
              p.setGreen(0);

          }
          //if there is a white left edge and the right isn't white, is an edge
          if (!isWhiteRight && isWhiteLeft){
              p.setBlue(0);
              p.setRed(255);
              p.setGreen(0);

          }
          //if there is a white upper edge and the lower isn't white, is an edge
          if (isWhiteUp && !isWhiteDown){
            p.setBlue(0);
            p.setRed(255);
            p.setGreen(0);

          }
          //if there is a white lower edge and the upper isn't white, is an edge
          if (!isWhiteUp && isWhiteDown){
            p.setBlue(0);
            p.setRed(255);
            p.setGreen(0);

          }
        }

      }
    }

    pic.show();
    pic.write("/Users/sunnyfeng/Documents/JavaCodes/circlehole-mod.jpg");

  }
}
