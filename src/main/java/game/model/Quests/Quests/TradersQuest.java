package game.model.Quests.Quests;

import game.model.Characters.Character;
import game.model.Quests.Quest;
import game.model.Quests.QuestFactory;

public class TradersQuest implements Quest {

    private final int experience;
    private final int task;

    private boolean status;

    private TradersQuest(Character character){
        task = character.getLevel() * 10;
        experience = character.getLevel()*100;
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
    public void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": kill " + getTask() + " enemy; reward: " + getReward();
    }

    public static QuestFactory questFactory = TradersQuest::new;
}
