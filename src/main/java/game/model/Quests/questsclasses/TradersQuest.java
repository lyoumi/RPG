package game.model.Quests.questsclasses;

import game.model.Characters.Character;
import game.model.Items.itemsclasses.Item;
import game.model.Quests.Quest;
import game.model.Quests.QuestFactory;

public class TradersQuest implements Quest {

    private final int experience;
    private final int task;

    private int last;

    private TradersQuest(Character character){
        task = character.getLevel() * 10;
        last = task;
        if (character.getLevel() < 8) experience = character.getLevel()*100;
        else experience = character.getLevel()*10000;
    }

    @Override
    public int getReward() {
        return experience;
    }

    @Override
    public int getTask() {
        return task;
    }

    @Override
    public int getLast() {
        last--;
        return last;
    }

    @Override
    public Item getItemReward() {
        return null;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": kill " + getTask() + " enemy; reward: " + getReward() + " exp";
    }

    public static QuestFactory questFactory = TradersQuest::new;
}
