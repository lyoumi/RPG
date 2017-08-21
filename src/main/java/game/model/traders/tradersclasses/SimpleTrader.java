package game.model.traders.tradersclasses;

import game.model.Characters.Character;
import game.model.Characters.characterclasses.Archer;
import game.model.Characters.characterclasses.Berserk;
import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.Item;
import game.model.Items.itemsclasses.armorsclasses.armors.LegendaryArmor;
import game.model.Items.itemsclasses.armorsclasses.boots.LegendaryBoots;
import game.model.Items.itemsclasses.armorsclasses.helmets.LegendaryHelmet;
import game.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import game.model.Items.itemsclasses.healclasses.healManaPoint.items.BigFlower;
import game.model.Items.itemsclasses.weaponsclasses.weapons.archer.LegendaryBow;
import game.model.Items.itemsclasses.weaponsclasses.weapons.berserk.LegendaryBoxingGloves;
import game.model.Items.itemsclasses.weaponsclasses.weapons.wizard.LegendaryStaff;
import game.model.Quests.Quest;
import game.model.Quests.questsclasses.LegendaryQuest;
import game.model.Quests.questsclasses.TradersQuest;
import game.model.traders.Trader;
import game.model.traders.TradersFactory;
import ext.RandomUniqueValue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс-реализация торговца.
 */
public class SimpleTrader implements Trader {

    /**
     * Создаем экземпляр класса для генерации случайного значения типа int.
     */
    private RandomUniqueValue randomUniqueValue = new RandomUniqueValue();

    /**
     * Мар, содержащая id предмета и предметы экипировки.
     */
    private Map<Integer, Item> priceListEquipmentObjects = new LinkedHashMap<>();

    /**
     * Мар, содержащая id предмета и предметы для восстановления здоровья/маны.
     */
    private Map<Integer, HealingItems> priceListHealingObjects = new LinkedHashMap<>();

    /**
     * Объект типа {@link Character} хранящий в себе имплементацию конкретного персонажа.
     */
    private Character character;

    /**
     * Конструктор, инициализирующий map'ы предметов и объект.
     * Также вызывается метод заполняющий map'ы объектами.
     *
     * @param character
     *              character implementation of {@link Character}
     */
    private SimpleTrader(Character character){
        this.character = character;
        generatePriceList();
    }

    /**
     * Метод, заполняющий map'ы предметами и их id.
     */
    private void generatePriceList(){
        if (character instanceof Archer) priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryBow.itemsFactory.createNewItem(character));
        else if (character instanceof Berserk) priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryBoxingGloves.itemsFactory.createNewItem(character));
        else priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryStaff.itemsFactory.createNewItem(character));
        priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryHelmet.itemsFactory.createNewItem(character));
        priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryBoots.itemsFactory.createNewItem(character));
        priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryArmor.itemsFactory.createNewItem(character));
        priceListHealingObjects.put(randomUniqueValue.nextUniqueInt(), BigFlower.healingHitPointItemsFactory.getNewHealingManaPointItem());
        priceListHealingObjects.put(randomUniqueValue.nextUniqueInt(), BigHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
    }

    @Override
    public Item getEquipmentItem(int id) {
        Item item = priceListEquipmentObjects.get(id);
        priceListEquipmentObjects.remove(id);
        return item;
    }

    @Override
    public List<HealingItems> getHealItems(int count, int id) {
        List<HealingItems> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(priceListHealingObjects.get(id));
        }
        return list;
    }

    @Override
    public Map<Integer, Item> getPriceListEquipmentObjects() {
        return priceListEquipmentObjects;
    }

    @Override
    public Map<Integer, HealingItems> getPriceListHealingObjects() {
        return priceListHealingObjects;
    }

    @Override
    public Quest getQuest(int index) {
        if (index==1) return TradersQuest.questFactory.createNewQuest(character);
        else return LegendaryQuest.questFactory.createNewQuest(character);
    }

    public static TradersFactory tradersFactory = SimpleTrader::new;
}
