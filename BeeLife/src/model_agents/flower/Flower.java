package model_agents.flower;

import jade.core.Agent;
import model_agents.controller.AbstractAgent;
import java.io.File;
import model_agents.controller.AbstractAgent.behaviour;
import static model_agents.controller.AbstractAgent.behaviour.*;
import static model_agents.controller.AbstractAgent.typeAgent.*;
import model_agents.controller.Environment;

/**
 *
 * @author Samuel
 */
public class Flower extends Agent implements AbstractAgent{
    
    private String sex = "F";
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
    
    public Flower() {
        this.height = 60;
        this.width = 30;
        this.pos_x = 0;
        this.pos_y = 600;
        this.tpAgent = FLOWER;
        this.image = new File("src/img/flower.png");
    }
    
    public Flower( int pos_x, int pos_y, String sex) {
        this.height = 60;
        this.width = 30;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.tpAgent = FLOWER;
        this.sex = sex;
        
        if (this.sex.equals("F")){
            this.image = new File("src/img/flower.png");
        }else {
            this.image = new File("src/img/flower_man.png");
        }
    }
    
    public String getSex() {
        return sex;
    }

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
