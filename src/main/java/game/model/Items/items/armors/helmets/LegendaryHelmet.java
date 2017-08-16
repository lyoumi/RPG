package game.model.Items.items.armors.helmets;

import game.model.Characters.Character;
import game.model.Items.EquipmentItems;
import game.model.Items.items.ItemsFactory;
import game.model.Items.items.armors.Armor;
import game.model.abilities.Magic;
import game.model.abilities.buffs.buffs.ArmorBuff;

import java.util.Random;

public class LegendaryHelmet implements Armor {


    private int defence;
    private int itemLevel;
    private Character character;
    private Magic magic;
    private final int price;


    private LegendaryHelmet(Character character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
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
        return LegendaryHelmet.class.getSimpleName() + ": DEF +" + getDefence();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = LegendaryHelmet::new;
}
