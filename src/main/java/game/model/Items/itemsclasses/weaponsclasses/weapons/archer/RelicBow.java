package game.model.Items.itemsclasses.weaponsclasses.weapons.archer;

import game.model.Characters.Character;
import game.model.Items.EquipmentItems;
import game.model.Items.itemsclasses.ItemsFactory;
import game.model.Items.itemsclasses.weaponsclasses.Weapons;
import game.model.abilities.Magic;
import game.model.abilities.buffs.buffsclasses.ArchersBuff;
import game.model.abilities.debuffs.debuffsclasses.damage.BurningJoe;

public class RelicBow implements Weapons {
    private int damage;
    private int itemLevel;
    private Character character;
    private Magic buff;
    private Magic magic;
    private final int price;

    private RelicBow(Character character){
        this.character = character;
        this.itemLevel = character.getLevel() + 20;
        this.damage = getItemLevel() * 9;
        this.price = 10000 * getItemLevel();
        this.buff = ArchersBuff.magicFactory.getMagicFactory(character.getLevel());
        this.magic = BurningJoe.magicFactory.getMagicFactory(getItemLevel());
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HANDS;
    }

    @Override
    public int getDamage() {
        return damage + ((BurningJoe)magic).getDamage();
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Magic getBuff() {
        return buff;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return getName() + ": " + " ATK +" + getDamage();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = RelicBow::new;
}
