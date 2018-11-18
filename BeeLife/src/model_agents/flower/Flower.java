package model_agents.flower;

import model_agents.controller.AbstractAgent;
import static model_agents.controller.AbstractAgent.typeAgent.FLOWER;
import java.io.File;

/**
 *
 * @author Samuel
 */
public class Flower extends AbstractAgent {
    
    private String sex = "F";
    
    public Flower() {
        super.setHeight(60);
        super.setWidth(30);
        super.setPos_x(0);
        super.setPos_y(600);
        super.setTpAgent(FLOWER);
        super.setImage(new File("src/img/flower.png"));
    }
    
    public Flower( int pos_x, int pos_y, String sex) {
        super.setHeight(60);
        super.setWidth(30);
        super.setPos_x(0);
        super.setPos_y(600);
        super.setTpAgent(FLOWER);
        this.sex = sex;
        
        if (this.sex.equals("F")){
            super.setImage(new File("src/img/flower.png"));
        }else {
            super.setImage(new File("src/img/flower_man.png"));
        }
    }
    
    public String getSex() {
        return sex;
    }
    
}
