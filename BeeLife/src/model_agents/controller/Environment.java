package model_agents.controller;

import java.util.ArrayList;
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

    public Environment(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm) {
        this.bees = new ArrayList<>();
        this.flowers = new ArrayList<>();
        this.birds = new ArrayList<>();
        this.worms = new ArrayList<>(); 
    }

    
          
}
