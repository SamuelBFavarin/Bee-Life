package model_agents.controller;

import java.util.List;
import model_agents.bee.Bee;
import model_agents.bird.Bird;
import model_agents.flower.Flower;
import model_agents.worm.Worm;

/**
 * @author Vinicius
 */
public class Environment{
    
    private final List<Bee> bees;
    private final List<Flower> flowers;
    private final List<Bird> birds;
    private final List<Worm> worms;

    public Environment(List<Bee> bees, List<Flower> flowers, List<Bird> birds, List<Worm> worms) {
        this.bees = bees;
        this.flowers = flowers;
        this.birds = birds;
        this.worms = worms;
    }

    
          
}
