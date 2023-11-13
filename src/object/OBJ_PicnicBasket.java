package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PicnicBasket extends SuperObject {

    public OBJ_PicnicBasket() {

        name = "PicnicBasket";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/picnicBasket.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
