package model_agents.controller;

import jade.core.Agent;
import java.io.File;

/**
 * @author Vinicius
 * Responsável por controlar quem está em cada X,Y do programa
 */
public class Environment {
    
    public enum TypeAgents{
        BEE, BIRD, FLOWER, WORM
    }
    
    private TypeAgents typeAgent;
    private Agent agent;
    private String nickName;
    private File image;
    private final int height;
    private final int width;

    public Environment(TypeAgents typeAgent, Agent agent, int width, int height) {
        this.typeAgent = typeAgent;
        this.agent = agent;
        this.width = width;
        this.height = height;
    }

    public TypeAgents getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(TypeAgents typeAgent) {
        this.typeAgent = typeAgent;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getNickName() {
        return nickName;
    }

    public File getImage() {
        return image;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public void configEnvironment(int number) {
        switch(typeAgent){
            case BEE: 
                this.nickName = "Bee" + String.valueOf(number);
                this.image = new File("src/img/bee.png");
                break;
            case BIRD: this.nickName = "Bird" + String.valueOf(number);
                this.image = new File("src/img/bird.gif");
                break;
            case FLOWER: this.nickName = "Flower" + String.valueOf(number);
                this.image = new File("src/img/flower.png");
                break;
            case WORM: this.nickName = "Worm" + String.valueOf(number);
                this.image = new File("src/img/worm.png");
                break;
        }      
    }
          
}
