package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Currypanman extends SuperObject {

    public OBJ_Currypanman() {

        name = "CurrypanMan";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/currypanman.png"));
        } catch(
                IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
