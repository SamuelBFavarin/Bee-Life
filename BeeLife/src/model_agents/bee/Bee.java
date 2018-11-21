package model_agents.bee;

import jade.core.Agent;
import java.io.File;
import java.util.List;
import model_agents.flower.Flower;
import model_agents.environment.AbstractAgent;
import model_agents.environment.AbstractAgent.behaviour;
import static model_agents.environment.AbstractAgent.behaviour.*;
import model_agents.environment.AbstractAgent.typeAgent;
import static model_agents.environment.AbstractAgent.typeAgent.*;
import model_agents.environment.Environment;

/**
 *
 * @author Samuel
 */
public class Bee extends Agent implements AbstractAgent{
    
    private Flower current_flower = null;
    private Flower last_flower = null;
    private String sex_last_flower = null;
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
   
    public Bee(int pos_x, int pos_y) {
        
        this.height = 30;
        this.width = 30;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = 2;
        this.direction_y = -1;
        this.state = SEARCH;
        this.tpAgent = BEE;
        this.image = new File("src/img/bee.png");
           
        if (Math.random()*100 > 50){
            this.direction_x = 1;
        } else {
            this.direction_x = -1;
        }
    }
    
    @Override
    protected void setup(){
        addBehaviour(new BeeSearch(this, 100));
        addBehaviour(new BeePollinate(this, 100));
        addBehaviour(new BeeToHive(this, 100));
        addBehaviour(new BeeInHive(this, 100));    
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

    public String getSex_last_flower() {
        return sex_last_flower;
    }

    public void setSex_last_flower(String sex_last_flower) {
        this.sex_last_flower = sex_last_flower;
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

    @Override
    public void setAbstractState(behaviour state) {
        this.state = state;
    }

    @Override
    public typeAgent getTpAgent() {
        return tpAgent;
    }

    @Override
    public void setTpAgent(AbstractAgent.typeAgent tpAgent) {
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
    
    public void alterImageDirection(){
        if (this.direction_x > 0){
            this.image = new File("src/img/bee.png");
        }else{
            this.image = new File("src/img/bee2.png");
        }
    }

    public Flower getCurrent_flower() {
        return current_flower;
    }

    public void setCurrent_flower(Flower current_flower) {
        this.current_flower = current_flower;
    }

    public Flower getLast_flower() {
        return last_flower;
    }

    public void setLast_flower(Flower last_flower) {
        this.last_flower = last_flower;
    }     
  
}
