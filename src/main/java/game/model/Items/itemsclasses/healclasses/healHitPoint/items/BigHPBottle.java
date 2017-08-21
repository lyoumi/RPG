package game.model.Items.itemsclasses.healclasses.healHitPoint.items;

import game.model.Characters.Character;
import game.model.Items.itemsclasses.healclasses.HealingItemsList;
import game.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItems;
import game.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItemsFactory;

public class BigHPBottle implements HealingHitPointItems {

    private final int price;

    private BigHPBottle(){
        this.price = 200;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.BigHPBottle;
    }

    @Override
    public void use(Character character) {
        character.setHitPoint(character.getMaxHitPoint());
    }

    public static HealingHitPointItemsFactory healingHitPointItemsFactory = BigHPBottle::new;

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public String toString(){
        return BigHPBottle.class.getSimpleName();
    }
}
