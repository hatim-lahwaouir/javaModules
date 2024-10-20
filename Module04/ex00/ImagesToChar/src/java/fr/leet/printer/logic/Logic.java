package fr.leet.printer.logic;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;


public class Logic{


    public static void print(char w,char b, String filePath){
        
        try
        {
            BufferedImage img = ImageIO.read(new File(filePath));

            for (int y = 0; y < img.getHeight(); y++){
                for (int x = 0; x < img.getWidth(); x++){
                    int color = img.getRGB(x, y);
                    if (color == Color.BLACK.getRGB()){
                        System.out.print(b);
                    }
                    if (color == Color.WHITE.getRGB() ){
                        System.out.print(w);
                    }
                }
                System.out.printf("\n");

            }


        }
        catch(IOException e){

        }


    }
}