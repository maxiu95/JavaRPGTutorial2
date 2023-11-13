package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Anpan extends SuperObject{

    public OBJ_Anpan() {
        name = "Anpan";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/anpan.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
