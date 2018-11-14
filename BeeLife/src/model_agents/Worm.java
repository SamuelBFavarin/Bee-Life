/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_agents;

import jade.core.AID;
import jade.core.Agent;
import java.io.File;

/**
 *
 * @author Samuel
 */
public class Worm extends Agent{
    
    private String path = "src/img/worm.png";
    private File image = new File("src/img/worm.png");
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private static final int speed = 1;
    private String localName;
    private AID aid;
    private int id;
    private int direction_x = -1;
    private int direction_y = 1;
    private String state = "search";
    public int percent_no_infect = 95;
    
    public Worm( int pos_x, int pos_y) {
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

    public AID getAid() {
        return aid;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public static int getSpeed() {
        return speed;
    }

    public int getDirectionX() {
        return direction_x;
    }

    public int getDirectionY() {
        return direction_y;
    }
    
    public String getStateWorm() {
        return state;
    }
    
    public void setStateWorm(String state){
        this.state = state;
    }
    

    public void setDirectionX(int direction_x) {
        this.direction_x = direction_x;
    }

    public void setDirectionY(int direction_y) {
        this.direction_y = direction_y;
    } 

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public File getImage() {
        return image;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }
    

    public void setDirection_y(int direction_y) {
        this.direction_y = direction_y;
    }
    
    
    
    
}
