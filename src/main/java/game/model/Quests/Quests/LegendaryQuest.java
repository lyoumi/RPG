package game.model.Quests.Quests;

import game.model.Characters.Character;
import game.model.Characters.characters.Archer;
import game.model.Characters.characters.Berserk;
import game.model.Items.items.Item;
import game.model.Items.items.armors.armors.RelicArmor;
import game.model.Items.items.armors.boots.RelicBoots;
import game.model.Items.items.armors.helmets.RelicHelmet;
import game.model.Items.items.weapons.weapons.archer.RelicBow;
import game.model.Items.items.weapons.weapons.berserk.RelicBoxingGloves;
import game.model.Items.items.weapons.weapons.wizard.RelicStaff;
import game.model.Quests.Quest;
import game.model.Quests.QuestFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LegendaryQuest implements Quest {

    private Random random = new Random();

    private final int experience;
    private final int task;

    private Character character;

    private int last;

    private LegendaryQuest(Character character){
        this.character = character;
        task = character.getLevel() * 1000;
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
        System.out.println(last);
        return last;
    }

    @Override
    public Item getItemReward() {
        List<Item> rewardList = new ArrayList<>();

        if (character instanceof Archer) rewardList.add(RelicBow.itemsFactory.createNewItem(character));
        else if (character instanceof Berserk) rewardList.add(RelicBoxingGloves.itemsFactory.createNewItem(character));
        else rewardList.add(RelicStaff.itemsFactory.createNewItem(character));
        rewardList.add(RelicHelmet.itemsFactory.createNewItem(character));
        rewardList.add(RelicArmor.itemsFactory.createNewItem(character));
        rewardList.add(RelicBoots.itemsFactory.createNewItem(character));

        return rewardList.get(random.nextInt(rewardList.size()));
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": kill " + getTask() + " enemy; reward: " + getReward() + " exp";
    }

    public static QuestFactory questFactory = LegendaryQuest::new;
}
