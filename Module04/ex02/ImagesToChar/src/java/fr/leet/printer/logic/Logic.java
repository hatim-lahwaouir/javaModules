package fr.leet.printer.logic;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;

// jcolor 
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;



import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


class Colors{
    static Map<String, int[] > colorMap = new HashMap<>(); 

    static {
        colorMap.put("red", new int[]{255, 0, 0});
        colorMap.put("green", new int[]{0, 255, 0});
        colorMap.put("blue", new int[]{0, 0, 255});
        colorMap.put("yellow", new int[]{255, 255, 0});
        colorMap.put("cyan", new int[]{0, 255, 255});
        colorMap.put("magenta", new int[]{255, 0, 255});
        colorMap.put("black", new int[]{0, 0, 0});
        colorMap.put("white", new int[]{255, 255, 255});
        colorMap.put("gray", new int[]{128, 128, 128});
        colorMap.put("orange", new int[]{255, 165, 0});
    }

    public static int[] getRGB(String color) {
        return colorMap.getOrDefault(color.toLowerCase(), null);
    }
}



public class Logic{

    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    public static void print(String white , String black, String filePath){
    
        int [] whiteColor = Colors.getRGB(white);
        int [] blackColor = Colors.getRGB(black);
        if (whiteColor == null){
            exit(white + "  this color can't be found sorry !");
        }

        if (blackColor == null){
            exit(black + "  this color can't be found sorry !");
        }
        try
        {
            BufferedImage img = ImageIO.read(new File(filePath));

            for (int y = 0; y < img.getHeight(); y++){
                for (int x = 0; x < img.getWidth(); x++){
                    int color = img.getRGB(x, y);
                    if (color == Color.BLACK.getRGB()){
                        System.out.printf(Ansi.colorize(" ", Attribute.BACK_COLOR(blackColor[0],blackColor[1],blackColor[2])));
                    }
                    if (color == Color.WHITE.getRGB() ){
                        System.out.printf(Ansi.colorize(" ", Attribute.BACK_COLOR(whiteColor[0],whiteColor[1],whiteColor[2])));
                    }
                }
                System.out.printf("\n");

            }


        }
        catch(IOException e){

        }


    }
}