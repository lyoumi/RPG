package game.model.Characters.characterclasses;

import game.model.Characters.CharacterFactory;
import game.model.Characters.CharacterNames;
import game.model.Characters.Character;
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
public class Berserk implements Character, UsingItems, Equipment {

    private Random random = new Random();

    private String name;
    private int agility = 10;
    private int intelligence = 11;
    private int power = 23;
    private long experience = 0;
    private int level = 1;
    private int baseDamage = getPower()*getMultiplierPower();
    private int hitPoint = getPower()*getMultiplierPower();
    private int mana = getIntelligence()*getMultiplierIntelligence();
    private Map<EquipmentItems, Item> equipmentItems = new HashMap<>();
    private Weapons weapon;
    private Armor armor;
    private int defence;
    private Magic magic;
    private int magicPoint;
    private final int multiplierAgility = 3;
    private final int multiplierIntelligence = 5;
    private final int multiplierPower = 7;
    private int expToNextLevel = 30;
    private int gold;
    private int count;
    private BuffMagic buffMagic;

    private int additionPower;
    private int additionIntelligence;
    private int additionAgility;

    private int countOfBigHitPointBottle;
    private int countOfMiddleHitPointBottle;
    private int countOfSmallHitPointBottle;

    private int countOfBigFlower;
    private int countOfMiddleFlower;
    private int countOfSmallFlower;
    private Quest quest;

    private Berserk(){
        List<CharacterNames> names = Collections.unmodifiableList(Arrays.asList(CharacterNames.values()));
        this.name = names.get(random.nextInt(names.size())).toString();
    }

    private int getMultiplierAgility() {
        return multiplierAgility;
    }

    private int getMultiplierPower() {
        return multiplierPower;
    }

    private int getMultiplierIntelligence() {
        return multiplierIntelligence;
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
            expToNextLevel = expToNextLevel * 4;
            setMagicPoint(getMagicPoint() + 1);
            setAgility(getAgility()+1);
            setIntelligence(getIntelligence()+1);
            setPower(getPower()+3);
            updateStats();
            return true;
        } else return false;
    }

    private int getAgility() {
        return agility + getAdditionAgility() + getBuffEffect(BuffClasses.agility);
    }

    private void setAgility(int agility) {
        this.agility = agility;
    }

    private int getIntelligence() {
        return intelligence + getAdditionIntelligence() + getBuffEffect(BuffClasses.intelligence);
    }

    private void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    private int getPower() {
        return power + getAdditionPower() + getBuffEffect(BuffClasses.power);
    }

    private void setPower(int power) {
        this.power = power;
    }

    private int notEnoughOfMana(){
        return 0;
    }

    private int getDefence() {
        defence = 0;
        for (Map.Entry<EquipmentItems, Item> entry :
                equipmentItems.entrySet()) {
            if (!entry.getValue().EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)) {
                defence += ((Armor) entry.getValue()).getDefence();
            }
        }
        return defence + getBuffEffect(BuffClasses.defence);
    }

    private int getSummaryAdditionParam(BuffClasses buffClass){
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

    private void setAdditionPower(){
        additionPower = getSummaryAdditionParam(BuffClasses.power);
    }

    private void setAdditionIntelligence(){
        additionIntelligence = getSummaryAdditionParam(BuffClasses.intelligence);
    }

    private void setAdditionAgility(){
        additionAgility = getSummaryAdditionParam(BuffClasses.agility);
    }

    private int getAdditionPower() {
        return additionPower;
    }

    private int getAdditionIntelligence() {
        return additionIntelligence;
    }

    private int getAdditionAgility() {
        return additionAgility;
    }

    private void updateStats(){
        setAdditionAgility();
        setAdditionIntelligence();
        setAdditionPower();
        setBaseDamage();
        setHitPoint(getPower()*getMultiplierPower());
        setManaPoint(getAgility()*getMultiplierIntelligence());
    }

    private void setBaseDamage(){
        baseDamage = getMultiplierPower()*getPower();
    }

    private int getBaseDamage(){
        return baseDamage;
    }

    private boolean isHealingBigHitPointBottle(){
        return getCountOfBigHitPointBottle() > 0;
    }

    private boolean isHealingMiddleHitPointBottle(){
        return getCountOfMiddleHitPointBottle() > 0;
    }

    private boolean isHealingSmallHitPointBottle(){
        return getCountOfSmallHitPointBottle() > 0;
    }

    private boolean isHealingBigManaPointBottle(){
        return getCountOfBigFlower() > 0;
    }

    private boolean isHealingMiddleManaPointBottle(){
        return getCountOfMiddleFlower() > 0;
    }

    private boolean isHealingSmallManaPointBottle(){
        return getCountOfSmallFlower() > 0;
    }

    private void activateBuff(Magic magic){
        buffMagic = (BuffMagic) magic;
        updateStats();
        count = buffMagic.getTimeOfAction();
    }

    private int getBuffEffect(BuffClasses buffClass){
        if (!Objects.equals(buffClass, null)){
            if (count > 0) return buffMagic.getEffect().getOrDefault(buffClass, 0);
            else return 0;
        } else return 0;
    }

    private int checkCountHealHitPoint(){
        return getCountOfBigHitPointBottle() + getCountOfMiddleHitPointBottle() + getCountOfSmallHitPointBottle();
    }

    private int checkCountManaPointBottle(){
        return getCountOfBigFlower() + getCountOfMiddleFlower() + getCountOfSmallFlower();
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

    @Override
    public int getCountOfBigHitPointBottle() {
        return countOfBigHitPointBottle;
    }

    @Override
    public int getCountOfMiddleHitPointBottle() {
        return countOfMiddleHitPointBottle;
    }

    @Override
    public int getCountOfSmallHitPointBottle() {
        return countOfSmallHitPointBottle;
    }

    @Override
    public int getCountOfBigFlower() {
        return countOfBigFlower;
    }

    @Override
    public int getCountOfMiddleFlower() {
        return countOfMiddleFlower;
    }

    @Override
    public int getCountOfSmallFlower() {
        return countOfSmallFlower;
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
    public int useMagic(Magic magic) {
        if (getManaPoint() >= magic.getManaCost()) {
            if (magic.getMagicClass().equals(MagicClasses.COMBAT)) {
                setManaPoint(getManaPoint() - magic.getManaCost());
                return ((InstantMagic) magic).getDamage();
            } else if (magic.getMagicClass().equals(MagicClasses.HEALING)) {
                setManaPoint(getManaPoint() - magic.getManaCost());
                return ((InstantMagic) magic).getDamage();
            } else {
                activateBuff(magic);
                return 0;
            }
        } else return notEnoughOfMana();
    }

    @Override
    public int getDamage() {
        if (equipmentItems.containsKey(EquipmentItems.HANDS)) {
            return getBaseDamage() + ((Weapons)equipmentItems.get(EquipmentItems.HANDS)).getDamage();
        }
        else return getBaseDamage();
    }

    @Override
    public int getManaPoint() {
        return mana;
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

    @Override
    public boolean add(HealingItems item) {
        if (item instanceof HealingManaPointItems) {
            if (checkCountManaPointBottle() < 50) {
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
            } else return false;
        } else if (item instanceof HealingHitPointItems) {
            if (checkCountHealHitPoint() < 50) {
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
        } else return false;
    }

    @Override
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

    @Override
    public void use(HealingItems item) {
        if (!Objects.equals(item, null)){
            if (item instanceof BigFlower) {
                item.use(this);
                countOfBigFlower--;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof MiddleFlower){
                item.use(this);
                countOfMiddleFlower--;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof SmallHPBottle){
                item.use(this);
                countOfSmallFlower--;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof BigHPBottle){
                item.use(this);
                countOfBigHitPointBottle--;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else if (item instanceof MiddleHPBottle){
                item.use(this);
                countOfMiddleHitPointBottle--;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                item.use(this);
                countOfSmallHitPointBottle--;
                try {
                    item.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    @Override
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

    @Override
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

    @Override
    public boolean checkHitPointBottle(){
        return getCountOfSmallHitPointBottle() > 0 || getCountOfMiddleHitPointBottle() > 0 || getCountOfBigHitPointBottle() > 0;
    }

    @Override
    public boolean checkManaPointBottle(){
        return getCountOfSmallFlower() != 0 || getCountOfMiddleFlower() != 0 || getCountOfBigFlower() != 0;

    }

    @Override
    public void equip(Item item) {
        if (item.EQUIPMENT_ITEMS().equals(EquipmentItems.HANDS)){
            weapon = (Weapons) item;
            if (equipmentItems.containsKey(item.EQUIPMENT_ITEMS())){
                Weapons usingWeapon = (Weapons) equipmentItems.get(EquipmentItems.HANDS);
                if (weapon.getDamage() > usingWeapon.getDamage()){
                    equipmentItems.remove(usingWeapon.EQUIPMENT_ITEMS());
                    try {
                        usingWeapon.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    equipmentItems.put(weapon.EQUIPMENT_ITEMS(), weapon);
                    updateStats();
                } else try {
                    weapon.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                equipmentItems.put(weapon.EQUIPMENT_ITEMS(), weapon);
                updateStats();
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
                    updateStats();
                } else try {
                    armor.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
                equipmentItems.put(armor.EQUIPMENT_ITEMS(), armor);
                updateStats();
            }
        }
        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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

    public static CharacterFactory characterFactory = Berserk::new;
}