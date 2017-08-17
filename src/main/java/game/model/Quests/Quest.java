package game.model.Quests;

import game.model.Items.items.Item;

public interface Quest {
    
    int getReward();
    
    int getTask();

    int getLast();

    Item getItemReward();

    void finalize() throws Throwable;
}
