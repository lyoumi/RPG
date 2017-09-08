package game.model.Characters;

import game.model.Items.EquipmentItems;
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

import java.io.Serializable;
import java.util.*;

/**
 * Created by pikachu on 13.07.17.
 */

/**
 * Базовый интерфейс для реализации классов персонажей.
 */
public abstract class Character implements Serializable{


    protected Random random = new Random();

    protected String name;
    protected int agility;
    protected int intelligence;
    protected int power;
    protected long experience;
    protected int level = 1;
    protected int baseDamage;
    protected int hitPoint = power*getMultiplierPower();
    protected int mana = intelligence*getMultiplierIntelligence();
    protected Map<EquipmentItems, Item> equipmentItems = new HashMap<>();
    protected Weapons weapon;
    protected Armor armor;
    protected int defence;
    protected Magic magic;
    protected int magicPoint;
    protected int multiplierAgility;
    protected int multiplierIntelligence;
    protected int multiplierPower;


    protected int expToNextLevel = 30;
    protected int gold = 100000;
    protected int count;
    protected BuffMagic buffMagic;
    protected int additionPower;

    protected int additionIntelligence;
    protected int additionAgility;
    protected int countOfBigHitPointBottle;

    protected int countOfMiddleHitPointBottle;
    protected int countOfSmallHitPointBottle;
    protected int countOfBigFlower;

    protected int countOfMiddleFlower;
    protected int countOfSmallFlower;
    protected Quest quest;

    protected void setAgility(int agility) {
        this.agility = agility;
    }

    protected void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setPower(int power) {
        this.power = power;
    }

    protected int getSummaryAdditionParam(BuffClasses buffClass){
        int summaryAdditionParam = 0;
        if(!Objects.equals(equipmentItems, null)){
            for (Map.Entry<EquipmentItems, Item> entry : equipmentItems.entrySet()) {
                if (!Objects.equals(entry.getValue().getBuff(), null)){
                    if (entry.getValue().getBuff().getMagicClass().equals(MagicClasses.BUFF)){
                        magic = entry.getValue().getBuff();
                        BuffMagic magic = (BuffMagic) this.magic;
                        if (magic.getEffect().containsKey(buffClass))
                            summaryAdditionParam += magic.getEffect().get(buffClass);
                    }
                }
            }
        }
        return summaryAdditionParam;
    }

    protected int getMultiplierAgility() {
        return multiplierAgility;
    }

    protected int getMultiplierPower() {
        return multiplierPower;
    }

    protected int getMultiplierIntelligence() {
        return multiplierIntelligence;
    }

    protected int getBuffEffect(BuffClasses buffClass){
        if (!Objects.equals(buffClass, null)){
            if (count > 0) return buffMagic.getEffect().getOrDefault(buffClass, 0);
            else return 0;
        } else return 0;
    }

    public boolean add(HealingItems item) {
        if (item instanceof HealingManaPointItems) {
            if (item instanceof BigFlower) {
                countOfBigFlower++;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof MiddleFlower) {
                countOfMiddleFlower++;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                countOfSmallFlower++;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            return true;
        } else if (item instanceof HealingHitPointItems) {
            if (item instanceof BigHPBottle) {
                countOfBigHitPointBottle++;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof MiddleHPBottle) {
                countOfMiddleHitPointBottle++;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                countOfSmallHitPointBottle++;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            return true;
        } else return false;
    }

    public boolean addAll(List<HealingItems> items) {
        if (Objects.equals(items, null)) return false;
        else {
            for (HealingItems item :
                    items) {
                if (item instanceof BigHPBottle) countOfBigHitPointBottle++;
                else countOfBigFlower++;
            }
            return true;
        }
    }

    public void use(HealingItems item) {
        if (!Objects.equals(item, null)){
            if (item instanceof BigFlower) {
                if (countOfBigFlower > 0){
                    item.use(this);
                    countOfBigFlower--;
                }
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof MiddleFlower){
                if (countOfMiddleFlower > 0){
                    item.use(this);
                    countOfMiddleFlower--;
                }
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof SmallHPBottle){
                if (countOfSmallFlower > 0){
                    item.use(this);
                    countOfSmallFlower--;
                }
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof BigHPBottle){
                if (countOfBigHitPointBottle > 0){
                    item.use(this);
                    countOfBigHitPointBottle--;
                }
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof MiddleHPBottle){
                if (countOfMiddleHitPointBottle > 0){
                    item.use(this);
                    countOfMiddleHitPointBottle--;
                }
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                if (countOfSmallHitPointBottle > 0){
                    item.use(this);
                    countOfSmallHitPointBottle--;
                }
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }


    public boolean healHitPoint() {
        if (isHealingBigHitPointBottle()){
            use(BigHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
            return true;
        } else if (isHealingMiddleHitPointBottle()){
            use(MiddleHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
            return true;
        } else if (isHealingSmallHitPointBottle()){
            use(SmallHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
            return true;
        } else return false;
    }

    public boolean healManaPoint() {
        if (isHealingBigManaPointBottle()){
            use(BigFlower.healingHitPointItemsFactory.getNewHealingManaPointItem());
            return true;
        } else if (isHealingMiddleManaPointBottle()){
            use(MiddleFlower.healingManaPointItemsFactory.getNewHealingManaPointItem());
            return true;
        } else if (isHealingSmallManaPointBottle()){
            use(SmallFlower.healingManaPointItemsFactory.getNewHealingManaPointItem());
            return true;
        } else return false;
    }


    public int useMagic(Magic magic) {
        if (getManaPoint() >= magic.getManaCost()) {
            if (magic.getMagicClass().equals(MagicClasses.COMBAT)) {
                setManaPoint(getManaPoint() - magic.getManaCost());
                return ((InstantMagic) magic).getDamage();
            } else {
                setManaPoint(getManaPoint() - magic.getManaCost());
                return ((InstantMagic) magic).getDamage();
            }
        } else return notEnoughOfMana();
    }

    protected void activateBuff(Magic magic){
        buffMagic = (BuffMagic) magic;
        count = buffMagic.getTimeOfAction();
    }

    private int notEnoughOfMana(){
        return 0;
    }


    private boolean isHealingBigHitPointBottle(){
        return countOfBigHitPointBottle > 0;
    }

    private boolean isHealingMiddleHitPointBottle(){
        return countOfMiddleHitPointBottle > 0;
    }

    private boolean isHealingSmallHitPointBottle(){
        return countOfSmallHitPointBottle > 0;
    }

    private boolean isHealingBigManaPointBottle(){
        return countOfBigFlower > 0;
    }

    private boolean isHealingMiddleManaPointBottle(){
        return countOfMiddleFlower > 0;
    }

    private boolean isHealingSmallManaPointBottle(){
        return countOfSmallFlower > 0;
    }

    public boolean equip(Item item) {
        if (item.EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)){
            weapon = (Weapons) item;
            if (equipmentItems.containsKey(item.EQUIPMENT_ITEMS())){
                Weapons usingWeapon = (Weapons) equipmentItems.get(EquipmentItems.HANDS);
                if (weapon.getDamage() > usingWeapon.getDamage()){
                    equipmentItems.remove(weapon.EQUIPMENT_ITEMS());
                    try {
                        usingWeapon.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    equipmentItems.put(weapon.EQUIPMENT_ITEMS(), weapon);
                    return true;
                } else {
                    try {
                        weapon.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    return false;
                }
            } else {
                equipmentItems.put(weapon.EQUIPMENT_ITEMS(), weapon);
                return true;
            }
        } else {
            armor = (Armor) item;
            if (equipmentItems.containsKey(item.EQUIPMENT_ITEMS())){
                Armor usingArmor = (Armor)equipmentItems.get(item.EQUIPMENT_ITEMS());
                if (armor.getDefence() > usingArmor.getDefence()){
                    equipmentItems.remove(armor.EQUIPMENT_ITEMS());
                    try {
                        usingArmor.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    equipmentItems.put(armor.EQUIPMENT_ITEMS(), armor);
                    return true;
                } else {
                    try {
                        armor.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    return false;
                }
            } else {
                equipmentItems.put(armor.EQUIPMENT_ITEMS(), armor);
                return true;
            }
        }
    }

    protected int getAgility() {
        return agility + getAdditionAgility() + getBuffEffect(BuffClasses.agility);
    }

    protected int getPower() {
        return power + getAdditionPower() + getBuffEffect(BuffClasses.power);
    }

    protected int getIntelligence() {
        return intelligence + getAdditionIntelligence() + getBuffEffect(BuffClasses.intelligence);
    }

    protected int getBaseDamage(){
        return baseDamage;
    }

    protected int getDefence() {
        defence = 0;
        for (Map.Entry<EquipmentItems, Item> entry :
                equipmentItems.entrySet()) {
            if (!entry.getValue().EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)) {
                defence += ((Armor) entry.getValue()).getDefence();
            }
        }
        return defence + getBuffEffect(BuffClasses.defence);
    }

    protected void setAdditionPower(){
        additionPower = getSummaryAdditionParam(BuffClasses.power);
    }

    protected void setAdditionIntelligence(){
        additionIntelligence = getSummaryAdditionParam(BuffClasses.intelligence);
    }

    protected void setAdditionAgility(){
        additionAgility = getSummaryAdditionParam(BuffClasses.agility);
    }

    protected int getAdditionPower() {
        return additionPower;
    }

    protected int getAdditionIntelligence() {
        return additionIntelligence;
    }

    protected int getAdditionAgility() {
        return additionAgility;
    }


    public abstract String getName();

    /**
     * Метод, возвращающий текущее количество золота у персонажа.
     *
     * @return
     *          int amount of gold.
     */
    public abstract int getGold();

    /**
     * Метод для изменения текущего количества золота.
     *
     * @param gold
     *              new amount of gold.
     */
    public abstract void setGold(int gold);

    /**
     * Метод для изменения текущего количества маны
     *
     * @return
     *          new amount of mana
     */
    public abstract int getManaPoint();

    /**
     * Метод для изменения текущего количества маны.
     *
     * @param mana
     *          new amount of mana.
     */
    public abstract void setManaPoint(int mana);

    /**
     * Метод, возвращаюший текущее количество маны
     *
     * @return
     *          int amount of skillPoint.
     */
    public abstract int getMagicPoint();

    /**
     * Метод для изменения текущего количества очков умений для прокачки персонажа.
     *
     * @param magicPoint
     *          new amount of magicPoint
     */
    public abstract void setMagicPoint(int magicPoint);

    /**
     * Метод, возвращающий текущее значение урона персонада.
     *
     * @return
     *          int damage
     */
    public abstract int getDamage();

    /**
     * Метод, возвращаюший значение полученного урона.
     *
     * @param damage
     *          int applied damage
     * @return
     *          int applied damage
     */
    public abstract int applyDamage(int damage);

    /**
     * Метод, возвращающий текущее количество здоровья.
     *
     * @return
     *          int hitPoint.
     */
    public abstract int getHitPoint();

    /**
     * Метод для изменения текущего количества здоровья.
     *
     * @param hitPoint
     *          new hitPoint value.
     */
    public abstract void setHitPoint(int hitPoint);

    /**
     * Метод, возвращающий максимальное количество здоровья с текущими зарактеристиками.
     *
     * @return
     *          maxHitPoint.
     */
    public abstract int getMaxHitPoint();

    /**
     * Метод, возвращающий максимальное количество маны с текущими зарактеристиками.
     *
     * @return
     *          maxManaPoint.
     */
    public abstract int getMaxManaPoint();

    public abstract int getCountOfBigHitPointBottle();

    public abstract int getCountOfMiddleHitPointBottle();

    public abstract int getCountOfSmallHitPointBottle();

    public abstract int getCountOfBigFlower();

    public abstract int getCountOfMiddleFlower();

    public abstract int getCountOfSmallFlower();

    public abstract int getCountOfHealingItems();

    /**
     * Метод, вызываюшийся после убийства монстра и добавлении нового опыта.
     *
     * @param experience
     *          new amount of experience.
     */
    public abstract boolean experienceDrop(double experience);

    /**
     * Метод, возвращающий текущий уровень героя.
     *
     * @return
     *          int current level.
     */
    public abstract int getLevel();

    /**
     * Метод, изменяющий текущее количество урона.
     *
     * @param damage
     *          new damage.
     */
    public abstract void setDamage(int damage);

    /**
     * Проверка на наличие предметов для восстановления здоровья.
     *
     * @return
     *          boolean result.
     */
    public abstract boolean checkHitPointBottle();

    /**
     * Проверка на наличие предметов для восстановления маны.
     *
     * @return
     *          boolean result.
     */
    public abstract boolean checkManaPointBottle();

    public abstract double expToNextLevel();

    public abstract void setQuest(Quest quest);

    public abstract Quest getQuest();
}
