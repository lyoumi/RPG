package game.model.Items.itemsclasses.healclasses.healManaPoint.items;

import game.model.Characters.Character;
import game.model.Items.itemsclasses.healclasses.HealingItemsList;
import game.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItems;
import game.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItemsFactory;

public class MiddleFlower implements HealingManaPointItems{

    private final int price;

    private MiddleFlower(){
        this.price = 150;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.MiddleFlower;
    }

    @Override
    public void use(Character character) {
        character.setManaPoint(character.getManaPoint() + character.getMaxManaPoint()/2);
    }

    public static HealingManaPointItemsFactory healingManaPointItemsFactory = MiddleFlower::new;

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public String toString(){
        return MiddleFlower.class.getSimpleName();
    }
}
