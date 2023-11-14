package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Ojisan extends SuperObject {

    public OBJ_Ojisan() {

        name = "Ojisan";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/jamojisan.png"));
        } catch(
                IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
