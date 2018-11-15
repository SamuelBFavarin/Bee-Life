package behaviour;

/**
 *
 * @author vinicius
 */
public interface Behaviour {
    
    public enum behaviour{
        PEACE, ATACK
    }
    
    void addBehaviourBird(behaviour tp);
    
}
