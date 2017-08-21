package game.model.Monsters.equipment.equipmentclasses;

import game.model.Characters.Character;
import game.model.Characters.characterclasses.Archer;
import game.model.Characters.characterclasses.Berserk;
import game.model.Items.EquipmentItems;
import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.Item;
import game.model.Items.itemsclasses.armorsclasses.armors.IronChest;
import game.model.Items.itemsclasses.armorsclasses.boots.IronBoots;
import game.model.Items.itemsclasses.armorsclasses.helmets.IronHelmet;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.MiddleHPBottle;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.SmallHPBottle;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.BigFlower;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.MiddleFlower;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.SmallFlower;
import game.model.Items.itemsclasses.weaponsclasses.weapons.archer.Bow;
import game.model.Items.itemsclasses.weaponsclasses.weapons.archer.Sword;
import game.model.Items.itemsclasses.weaponsclasses.weapons.berserk.BoxingGloves;
import game.model.Items.itemsclasses.weaponsclasses.weapons.berserk.Gloves;
import game.model.Items.itemsclasses.weaponsclasses.weapons.wizard.MagicWand;
import game.model.Items.itemsclasses.weaponsclasses.weapons.wizard.Staff;
import game.model.Monsters.equipment.MonsterEquipment;
import game.model.Monsters.equipment.MonsterEquipmentFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SimpleMonsterEquipment implements MonsterEquipment {

    private Random random = new Random();

    public HashMap<EquipmentItems, Item> initEquipment(Character character){
        HashMap<EquipmentItems, Item> equipment = new HashMap<>();
        int i = random.nextInt(10);
        if (character instanceof Archer){
            if ((i > 0) && (i < 3))
                equipment.put(Bow.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Bow.itemsFactory.createNewItem(character));
            else
                equipment.put(Sword.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Sword.itemsFactory.createNewItem(character));
        } else if (character instanceof Berserk){
            if ((i > 0) && (i < 3))
                equipment.put(BoxingGloves.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), BoxingGloves.itemsFactory.createNewItem(character));
            else
                equipment.put(Gloves.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Gloves.itemsFactory.createNewItem(character));
        } else {
            if ((i > 0) && (i < 3))
                equipment.put(Staff.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Staff.itemsFactory.createNewItem(character));
            else
                equipment.put(MagicWand.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), MagicWand.itemsFactory.createNewItem(character));
        }
        equipment.put(IronHelmet.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), IronHelmet.itemsFactory.createNewItem(character));
        equipment.put(IronChest.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), IronChest.itemsFactory.createNewItem(character));
        equipment.put(IronBoots.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), IronBoots.itemsFactory.createNewItem(character));
        return equipment;
    }

    public List<HealingItems> initializeItemList(){
        List<HealingItems> itemsList = new ArrayList<>();
        itemsList.add(BigHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
        itemsList.add(BigFlower.healingHitPointItemsFactory.getNewHealingManaPointItem());
        itemsList.add(MiddleHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
        itemsList.add(MiddleFlower.healingManaPointItemsFactory.getNewHealingManaPointItem());
        itemsList.add(SmallHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
        itemsList.add(SmallFlower.healingManaPointItemsFactory.getNewHealingManaPointItem());

        return  itemsList;
    }




    public static MonsterEquipmentFactory monsterEquipmentFactory = SimpleMonsterEquipment::new;
}
