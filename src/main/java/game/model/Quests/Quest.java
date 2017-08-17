package game.model.Quests;

public interface Quest {
    
    int getReward();
    
    int getTask();

    void finalize() throws Throwable;
}
