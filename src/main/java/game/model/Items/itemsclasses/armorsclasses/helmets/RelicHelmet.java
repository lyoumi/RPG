package game.model.Items.itemsclasses.armorsclasses.helmets;

import game.model.Characters.Character;
import game.model.Items.EquipmentItems;
import game.model.Items.itemsclasses.ItemsFactory;
import game.model.Items.itemsclasses.armorsclasses.Armor;
import game.model.abilities.Magic;
import game.model.abilities.buffs.buffsclasses.ArmorBuff;

public class RelicHelmet implements Armor {

    private int defence;
    private int itemLevel;
    private Character character;
    private Magic magic;
    private final int price;


    private RelicHelmet(Character character){
        this.character = character;
        this.itemLevel = character.getLevel() + 20;
        this.price = 10000*getItemLevel();
        this.defence = getItemLevel() * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(getItemLevel());
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HEAD;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public String getName() {
        return IronHelmet.class.getSimpleName();
    }

    @Override
    public Magic getBuff() {
        return magic;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String toString(){
        return this.getClass().getSimpleName() + ": DEF +" + getDefence();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = RelicHelmet::new;
}
