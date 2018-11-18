package behaviour;

/**
 *
 * @author vinicius
 */
public interface Behaviour {
   
    
    public enum behaviour{
        PEACE, ATACK, SEARCH, POLLINATING, TO_HIVE, IN_HIVE, INFECT, DIE
    }
    
    void addBehaviourBird(behaviour tp);
    
}
