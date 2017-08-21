package game.model.Items.itemsclasses;

import game.model.Characters.Character;
import game.model.Items.itemsclasses.healclasses.HealingItemsList;

public interface HealingItems {

    /**
     * Метод, который возвращает стоимость предмета.
     *
     * @return
     *          price of current item.
     */
    int getPrice();

    /**
     * Метод, возвращающий тип этого предмета из {@link HealingItemsList}
     *
     * @return
     *          item type from {@link HealingItemsList}
     */
    HealingItemsList getHealingItemClass();

    /**
     * Метод, реализующий использование  текущего предмета.
     *
     * @param character
     *              character implementation of {@link Character}
     */
    void use(Character character);

    void finalize() throws Throwable ;
}
