package model_agents.controller;

import jade.core.Agent;
import java.io.File;

/**
 *
 * @author vinicius
 */
public class AbstractAgent extends Agent{
   
    public enum typeAgent{
        BEE, BIRD, FLOWER, WORM, HIVE
    }
    public enum behaviour{
        PEACE, ATACK, SEARCH, POLLINATING, TO_HIVE, IN_HIVE, INFECT, DIE
    }
    
    private File image;
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private behaviour state;
    private typeAgent tpAgent;
    private int direction_x;
    private int direction_y;
    private int speed;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public behaviour getAbstractState() {
        return state;
    }

    public void setAbstractState(behaviour state) {
        this.state = state;
    }

    public int getDirection_x() {
        return direction_x;
    }

    public void setDirection_x(int direction_x) {
        this.direction_x = direction_x;
    }

    public int getDirection_y() {
        return direction_y;
    }

    public void setDirection_y(int direction_y) {
        this.direction_y = direction_y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setTpAgent(typeAgent tpAgent) {
        this.tpAgent = tpAgent;
    }

    public typeAgent getTpAgent() {
        return tpAgent;
    }
                       
    public void addBehaviour(behaviour tp) {
        
    }
    
}
