package game.model.Items.itemsclasses.healclasses.healHitPoint.items;

import game.model.Characters.Character;
import game.model.Items.itemsclasses.healclasses.HealingItemsList;
import game.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItems;
import game.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItemsFactory;

public class SmallHPBottle implements HealingHitPointItems {

    private final int price;

    private SmallHPBottle(){
        this.price = 100;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.SmallHPBottle;
    }

    @Override
    public void use(Character character) {
        character.setHitPoint(character.getHitPoint() + character.getMaxHitPoint()/4);
    }

    public static HealingHitPointItemsFactory healingHitPointItemsFactory = SmallHPBottle::new;

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public String toString(){
        return SmallHPBottle.class.getSimpleName();
    }
}
