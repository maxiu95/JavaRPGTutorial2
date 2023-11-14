package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Shokupanman extends SuperObject {

    public OBJ_Shokupanman() {

        name = "ShokupanMan";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shokupanman.png"));
        } catch(
                IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
