package game.model.Items.itemsclasses.armorsclasses.boots;

import game.model.Characters.Character;
import game.model.Items.EquipmentItems;
import game.model.Items.itemsclasses.ItemsFactory;
import game.model.Items.itemsclasses.armorsclasses.Armor;
import game.model.abilities.Magic;
import game.model.abilities.buffs.buffsclasses.ArmorBuff;

public class LegendaryBoots implements Armor {

    private int defence;
    private int itemLevel;
    private Magic magic;
    private Character character;
    private final int price;

    private LegendaryBoots(Character character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
        this.price = 10000*getItemLevel();
        this.defence = getItemLevel() * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(getItemLevel());
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.LEGS;
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
        return IronBoots.class.getSimpleName();
    }

    @Override
    public Magic getBuff() {
        return magic;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return LegendaryBoots.class.getSimpleName() + ": DEF +" + getDefence();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = LegendaryBoots::new;
}
