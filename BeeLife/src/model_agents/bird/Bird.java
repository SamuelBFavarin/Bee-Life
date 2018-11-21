package model_agents.bird;

import jade.core.Agent;
import model_agents.environment.AbstractAgent;
import static model_agents.environment.AbstractAgent.behaviour.PEACE;
import static model_agents.environment.AbstractAgent.typeAgent.BIRD;
import java.io.File;
import model_agents.environment.AbstractAgent.behaviour;
import model_agents.environment.AbstractAgent.typeAgent;
import model_agents.environment.Environment;

/**
 *
 * @author Samuel
 */

public class Bird extends Agent implements AbstractAgent{
    
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
    private Environment environment;
    private String nickName;
    
    public Bird() {
        this.height = 80;
        this.width = 80;
        this.pos_x = 0;
        this.pos_y = 0;
        this.speed = 10;
        this.direction_y = -1;
        this.state = PEACE;
        this.tpAgent = BIRD;
        this.image = new File("src/img/bird.gif");
    }
    
    public Bird(int pos_x, int pos_y) {
        this.height = 80;
        this.width = 80;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = 10;
        this.direction_y = -1;
        this.state = PEACE;
        this.tpAgent = BIRD;
        this.image = new File("src/img/bird.gif");
        
        if (Math.random()*100 > 50){
            this.direction_x = 1;
        } else {
            this.direction_x = -1;
        }
    }
    
    @Override
    protected void setup(){
        addBehaviour(new BirdPeace(this, 100));
        addBehaviour(new BirdGoAtack(this, 100));
        addBehaviour(new BirdBackAtack(this, 100));          
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

    public void setState(behaviour state) {
        this.state = state;
    }

    @Override
    public typeAgent getTpAgent() {
        return tpAgent;
    }

    @Override
    public void setTpAgent(typeAgent tpAgent) {
        this.tpAgent = tpAgent;
    }

    public int getDirection_x() {
        this.alterImageDirection();
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

     public void alterImageDirection(){
        if (this.direction_x > 0){
            this.image = new File("src/img/bird.gif");
        }else{
            this.image = new File("src/img/bird2.gif");
        }
    }
    
}
