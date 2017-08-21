package game.model.Items.itemsclasses.healclasses.healManaPoint;

import game.model.Characters.Character;
import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.healclasses.HealingItemsList;

public interface HealingManaPointItems extends HealingItems{
    @Override
    int getPrice();

    @Override
    HealingItemsList getHealingItemClass();

    @Override
    void use(Character character);
}
