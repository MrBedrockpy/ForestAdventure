package ru.mrbedrockpy.mygame.game.inventory;

import ru.mrbedrockpy.mygame.game.item.Item;

import java.util.List;

public class Inventory {

    private final int maxSlots = 8;

    private final List<Item> slots;

    private Item weaponSlot;

    public Inventory(List<Item> slots, Item weaponSlot) {
        this.slots = slots;
        this.weaponSlot = weaponSlot;
    }

    public boolean addItem(Item item) {
        if (slots.size() == maxSlots) {
            return false;
        }
        slots.add(item);
        return true;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public List<Item> getSlots() {
        return slots;
    }

    public Item getWeaponSlot() {
        return weaponSlot;
    }

    public void setWeaponSlot(Item weaponSlot) {
        this.weaponSlot = weaponSlot;
    }
}
