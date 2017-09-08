package game.model.Characters.characterclasses;

import game.model.Characters.Character;
import game.model.Characters.CharacterFactory;
import game.model.Characters.CharacterNames;
import game.model.Items.Equipment;
import game.model.Items.EquipmentItems;
import game.model.Items.UsingItems;
import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.Item;
import game.model.Items.itemsclasses.armorsclasses.Armor;
import game.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItems;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.MiddleHPBottle;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.SmallHPBottle;
import game.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItems;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.BigFlower;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.MiddleFlower;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.SmallFlower;
import game.model.Items.itemsclasses.weaponsclasses.Weapons;
import game.model.Quests.Quest;
import game.model.abilities.Magic;
import game.model.abilities.MagicClasses;
import game.model.abilities.buffs.BuffClasses;
import game.model.abilities.buffs.BuffMagic;
import game.model.abilities.instants.InstantMagic;

import java.util.*;

/**
 * Created by pikachu on 13.07.17.
 */
public class Archer extends Character implements UsingItems, Equipment{


    private Archer(){

        agility = 22;
        intelligence = 13;
        power = 11;

        multiplierAgility = 2;
        multiplierIntelligence = 11;
        multiplierPower = 7;

        updateStats();

        List<CharacterNames> names = Collections.unmodifiableList(Arrays.asList(CharacterNames.values()));
        name = names.get(random.nextInt(names.size())).toString();
    }

    private boolean expToNextLevelReady(){
        return getExperience() >= expToNextLevel;
    }

    private double getExperience() {
        return experience;
    }

    private boolean setExperience(double experience) {
        if ((this.experience += experience) < Integer.MAX_VALUE){
            this.experience += experience;
            changeLevel();
            return false;
        } else return true;
    }

    private boolean changeLevel(){
        if (expToNextLevelReady()) {
            level++;
            expToNextLevel = (expToNextLevel * 4);
            setMagicPoint(getMagicPoint() + 1);
            setAgility(getAgility()+3);
            setIntelligence(getIntelligence()+2);
            setPower(getPower()+2);
            updateStats();
            return true;
        } else return false;
    }


    private void updateStats(){
        setAdditionAgility();
        setAdditionIntelligence();
        setAdditionPower();
        setHitPoint(getPower()*getMultiplierPower());
        setDamage(getAgility()*getMultiplierAgility());
        setManaPoint(getIntelligence()*getMultiplierIntelligence());
    }


    @Override
    public double expToNextLevel() {
        return (expToNextLevel - getExperience());
    }

    @Override
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    @Override
    public Quest getQuest() {
        return quest;
    }

    public void setManaPoint(int mana) {
        if (mana > getMaxManaPoint()) this.mana = getMaxManaPoint();
        else this.mana = mana;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void setGold(int gold) {
        if (gold < Integer.MAX_VALUE) this.gold = gold;
        else this.gold = Integer.MAX_VALUE;
    }

    @Override
    public int getMagicPoint(){
        return magicPoint;
    }

    @Override
    public void setMagicPoint(int magicPoint) {
        this.magicPoint = magicPoint;
    }

    @Override
    public boolean experienceDrop(double experience){
        return setExperience(experience);
    }

    @Override
    public int getLevel() {
        return level;
    }


    @Override
    public int getManaPoint() {
        return mana;
    }

    @Override
    public int getDamage() {
        if (equipmentItems.containsKey(EquipmentItems.HANDS)) return getBaseDamage() + ((Weapons)equipmentItems.get(EquipmentItems.HANDS)).getDamage();
        else return getBaseDamage();
    }

    @Override
    public void setDamage(int damage) {
        this.baseDamage = damage;
    }

    @Override
    public int applyDamage(int damage)  {
        if (count > 0) count--;
        int applyingDamage = damage - getDefence();
        if (applyingDamage < 0) return 0;
        else return applyingDamage;
    }

    @Override
    public int getHitPoint() {
        if (hitPoint < 0) return 0;
        else return hitPoint;
    }

    @Override
    public void setHitPoint(int hitPoint) {
        if (hitPoint >= getMaxHitPoint()) this.hitPoint = getMaxHitPoint();
        else this.hitPoint = hitPoint;
    }

    @Override
    public int getMaxHitPoint() {
        return getPower()*getMultiplierPower();
    }

    @Override
    public int getMaxManaPoint() {
        return getIntelligence()*getMultiplierIntelligence();
    }

    public int getCountOfBigHitPointBottle() {
        return countOfBigHitPointBottle;
    }

    public int getCountOfMiddleHitPointBottle() {
        return countOfMiddleHitPointBottle;
    }

    public int getCountOfSmallHitPointBottle() {
        return countOfSmallHitPointBottle;
    }

    public int getCountOfBigFlower() {
        return countOfBigFlower;
    }

    public int getCountOfMiddleFlower() {
        return countOfMiddleFlower;
    }

    public int getCountOfSmallFlower() {
        return countOfSmallFlower;
    }

    @Override
    public int useMagic(Magic magic) {
        if (magic.getMagicClass().equals(MagicClasses.BUFF)) {
            activateBuff(magic);
            updateStats();
            return 0;
        }
        return super.useMagic(magic);
    }

    @Override
    public int getCountOfHealingItems() {
        return getCountOfBigHitPointBottle() +
                getCountOfMiddleHitPointBottle() +
                getCountOfSmallHitPointBottle() +
                getCountOfBigFlower() +
                getCountOfMiddleFlower() +
                getCountOfSmallFlower();
    }

    @Override
    public boolean checkHitPointBottle(){
        return getCountOfSmallHitPointBottle() > 0 || getCountOfMiddleHitPointBottle() > 0 || getCountOfBigHitPointBottle() > 0;
    }

    @Override
    public boolean checkManaPointBottle(){
        return getCountOfSmallFlower() > 0 || getCountOfMiddleFlower() > 0 || getCountOfBigFlower() > 0;

    }

    @Override
    public boolean equip(Item item) {
        if (super.equip(item)) {
            updateStats();
            return true;
        } else return false;
    }

    @Override
    public void unEquip() {

    }

    @Override
    public Map<EquipmentItems, Item> showEquipment() {
        return equipmentItems;
    }

    @Override
    public String toString(){
        return "Class: " + this.getClass().getSimpleName() +
                " " + getName() +
                "; HP " + String.valueOf(getHitPoint()) +
                "; MP " + getManaPoint() +
                "; DMG: " + getDamage() +
                "; DEF: " + getDefence() +
                "; Lvl: " + String.valueOf(getLevel()) +
                "; Exp to next level: " + expToNextLevel() +
                "; GOLD: " + getGold();
    }

    public static CharacterFactory characterFactory = Archer::new;
}
