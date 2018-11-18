package model_agents.bee;

import behaviour.Behaviour;
import static behaviour.Behaviour.behaviour.SEARCH;
import jade.core.Agent;
import java.io.File;
import java.util.List;
import model_agents.flower.Flower;

/**
 *
 * @author Samuel
 */
public class Bee extends Agent implements Behaviour{
       
    private File image = new File("src/img/bee.png");
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private final int speed = 2;
    private int direction_x = 1;
    private int direction_y = -1;
    private behaviour state = SEARCH;
    private String sex_last_flower = null;

    
    public Bee( int pos_x, int pos_y) {
        this.height = 30;
        this.width = 30;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        
        if (Math.random()*100 > 50){
            this.direction_x = 1;
        } else {
            this.direction_x = -1;
        }
    }
    
    @Override
    protected void setup(){
        
    }
    
    public boolean haveFlower(int posx, int posy, List<Flower> flowers){
        return false;
    };
    
    public boolean havePolen(Flower flower){
        return false;
    };
    
    public boolean isMasc(Flower flower){
        return false;
    };
    
    public boolean nearMascFlower(List<Flower> flowers){
      return false;  
    };

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

    public int getSpeed() {
        return speed;
    }

    public int getDirectionX() {
        return direction_x;
    }

    public int getDirectionY() {
        return direction_y;
    }

    public void setDirectionX(int direction_x) {
        this.direction_x = direction_x;
    }

    public void setDirectionY(int direction_y) {
        this.direction_y = direction_y;
    }

    public behaviour getStateBee() {
        return state;
    }

    public void setStateBee(behaviour state) {
        this.state = state;
    }

    public String getSex_last_flower() {
        return sex_last_flower;
    }

    public void setSex_last_flower(String sex_last_flower) {
        this.sex_last_flower = sex_last_flower;
    }

    @Override
    public void addBehaviourBird(behaviour tp) {
       
    }
     
}
