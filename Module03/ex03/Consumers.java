import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


class Consumers implements Runnable{

    static int incr = 1;
    Info info;
    int id;

    Consumers(Info info){
        this.info = info;
        this.id = Consumers.incr++;
    }


    public void run(){
        while (true)
        {
            String img_url  = info.getUrl();

            Random rand = new Random();


            if (img_url == null)
                return;
            Path output = Paths.get("images/").resolve("image_from_" + id  + "_" + rand.nextInt(1000) + ".jpg");

            try (InputStream inputStream = (new URL(img_url).openStream())){
                Files.copy(inputStream, output);
            }
            catch (IOException e) {

                System.err.printf("An IOException !\n");
                System.exit(-1);
            }

        }
    }
}