package model_agents.flower;

import jade.core.Agent;
import java.io.File;

/**
 *
 * @author Samuel
 */
public class Flower extends Agent {
    
    private File image = new File("src/img/flower.png");
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private String sex = "F";
    
    public Flower() {
        this.height = 60;
        this.width = 30;
        this.pos_x = 0;
        this.pos_y = 600;
    }
    
    public Flower( int pos_x, int pos_y, String sex) {
        this.height = 60;
        this.width = 30;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.sex = sex;
        
        if (this.sex.equals("F")){
            this.image = new File("src/img/flower.png");
        }else {
            this.image = new File("src/img/flower_man.png");
        }
    }
    
    public File getImage() {
        return image;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getSex() {
        return sex;
    }
    
}
