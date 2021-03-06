package game.model.Items;

import game.model.Items.itemsclasses.Item;

import java.util.Map;

/**
 * Created by pikachu on 14.07.17.
 */

/**
 * Интерфейс реализующий работу с экипировкой.
 */
public interface Equipment {

    /**
     * Метод реализующий экипирование предмета
     *
     * @param item
     *          implementation of {@link Item}
     */
    boolean equip(Item item);

    /**
     * Метод, реализующий удаление предмета из экипировки.
     */
    void unEquip();

    /**
     * Метод, возвращающий текущую экипировку персонажа.
     *
     * @return
     *          current equipmentclasses.
     */
    Map<EquipmentItems, Item> showEquipment();
}
