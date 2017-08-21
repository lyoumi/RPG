package game.model.Monsters.equipment;

import game.model.Characters.Character;
import game.model.Items.EquipmentItems;
import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.Item;

import java.util.HashMap;
import java.util.List;

public interface MonsterEquipment {

    HashMap<EquipmentItems, Item> initEquipment(Character character);

    List<HealingItems> initializeItemList();
}
