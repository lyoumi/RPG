package game.model.traders;

import game.model.Items.itemsclasses.HealingItems;
import game.model.Items.itemsclasses.Item;
import game.model.Quests.Quest;

import java.util.List;
import java.util.Map;

/**
 * Базовый интерфейс для реализации классов торговцев.
 */
public interface Trader {

    /**
     * Метод, возвращающий купленный предмет экипировки.
     *
     * @param id
     *          item id
     * @return
     *          equipmentclasses item
     */
    Item getEquipmentItem(int id);

    /**
     * Метод, возвращающий предметы для восстановления здоровья/маны в количестве заданным пользователем
     *
     * @param count
     *              count of itemsclasses purchased
     * @param id
     *              id of itemsclasses purchased
     * @return
     *              list of itemsclasses purchased
     */
    List<HealingItems> getHealItems(int count, int id);

    /**
     * Метод, возвращающий список с товаров и их id в {@link Map}
     *
     * @return
     *          priceList of equipmentclasses itemsclasses
     */
    Map<Integer, Item> getPriceListEquipmentObjects();

    /**
     * Метод, возвращающий список с товаров и их id в {@link Map}
     *
     * @return
     *          priceList of equipmentclasses itemsclasses
     */
    Map<Integer, HealingItems> getPriceListHealingObjects();

    Quest getQuest(int index);

}
