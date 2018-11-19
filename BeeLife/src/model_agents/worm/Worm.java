package model_agents.worm;

import jade.core.Agent;
import static model_agents.controller.AbstractAgent.behaviour.*;
import java.io.File;
import model_agents.controller.AbstractAgent;
import model_agents.controller.AbstractAgent.behaviour;
import static model_agents.controller.AbstractAgent.typeAgent.*;
import model_agents.controller.Environment;

/**
 *
 * @author Samuel
 */
public class Worm extends Agent implements AbstractAgent{
    
    public int percent_no_infect = 95;
    private File image;
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private AbstractAgent.behaviour state;
    private AbstractAgent.typeAgent tpAgent;
    private int direction_x;
    private int direction_y;
    private int speed;
    private Environment environment;
    private String nickName;
    
    public Worm( int pos_x, int pos_y) {
        
        this.height = 30;
        this.width = 30;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = 2;
        this.direction_y = -1;
        this.state = SEARCH;
        this.tpAgent = WORM;
        this.image = new File("src/img/worm.png");
       
        if (Math.random()*100 > 50){
            this.direction_x = 1;
        } else {
            this.direction_x = -1;
        }
    }
    
    @Override
    protected void setup(){
        
    }

    public int getPercent_no_infect() {
        return percent_no_infect;
    }

    public void setPercent_no_infect(int percent_no_infect) {
        this.percent_no_infect = percent_no_infect;
    }

    @Override
    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    @Override
    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    @Override
    public behaviour getAbstractState() {
        return state;
    }

    public void setState(AbstractAgent.behaviour state) {
        this.state = state;
    }

    @Override
    public AbstractAgent.typeAgent getTpAgent() {
        return tpAgent;
    }

    @Override
    public void setTpAgent(AbstractAgent.typeAgent tpAgent) {
        this.tpAgent = tpAgent;
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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public void setAbstractState(behaviour state) {
        this.state = state;
    }
    
    
   
}
