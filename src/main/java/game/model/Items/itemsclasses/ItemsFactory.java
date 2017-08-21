package game.model.Items.itemsclasses;

import game.model.Characters.Character;

public interface ItemsFactory {
    Item createNewItem(Character character);
}
