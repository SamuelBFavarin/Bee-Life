package model_agents.hive;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model_agents.controller.AbstractAgent;
import static model_agents.controller.AbstractAgent.typeAgent.HIVE;

/**
 *
 * @author Vinicius
 */
public class Hive extends AbstractAgent {

    public int hive_nectar = 0;
    
    public Hive() {
        super.setHeight(100);
        super.setWidth(80);
        super.setPos_x(20);
        super.setPos_y(300);
        super.setTpAgent(HIVE);
        super.setImage(new File("src/img/hive.png"));
    }
    
    public Image getImageHive(){
        try { 
            return ImageIO.read(this.getImage());
        } catch (IOException ex) {
            System.out.println("Erro em Hive.getImageHive()");
        }
        return null;
    }
    
       
}
