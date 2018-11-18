package model_agents.bird;

import jade.core.Agent;
import java.io.File;
import behaviour.Behaviour;

/**
 *
 * @author Samuel
 */

public class Bird extends Agent implements Behaviour{
    
    
    private File image = new File("src/img/bird.gif");
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private behaviour state = behaviour.PEACE;
    private int direction_x = 1;
    private int direction_y = -1;
    private final int speed = 10;
    

    public Bird() {
        this.height = 80;
        this.width = 80;
        this.pos_x = 0;
        this.pos_y = 0;
    }
    
    public Bird( int pos_x, int pos_y) {
        this.height = 80;
        this.width = 80;
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
    
    public void setDirectionX(int direction) {
        this.direction_x = direction;
    }
    
    public void setDirectionY(int direction) {
        this.direction_y = direction;
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
    
    public void setState(behaviour state){
        this.state = state;
    }

    public int getSpeed() {
        return speed;
    }
    
    public int getDirectionX() {
        return this.direction_x;
    }
    
    public int getDirectionY() {
        return this.direction_y;
    }
    
    public behaviour getStateBird() {
        return this.state;
    }

    @Override
    public void addBehaviourBird(behaviour tp) {
        switch(tp){
            case ATACK: 
                this.state = tp;
                addBehaviour(new BirdAtack(this));
                break;
            case PEACE: 
                this.state = tp;
                addBehaviour(new BirdPeace(this));
                break;
        }
    }
    
}
