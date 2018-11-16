package model_agents.controller;

/**
 * @author Vinicius
 * Responsável por controlar quem está em cada X,Y do programa
 */
public class Environment {
    
    public enum type_agents{
        BEE, BIRD, FLOWER, WORM
    }
    
    private type_agents agent;

    public Environment(type_agents agent) {
        this.agent = agent;
    }

    public type_agents getAgent() {
        return agent;
    }

    public void setAgent(type_agents agent) {
        this.agent = agent;
    }
       
}
