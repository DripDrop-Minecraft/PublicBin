package com.jerryio.publicbin.enums;

import org.bukkit.Material;

import com.jerryio.publicbin.interfaces.BinItemComparison;

@SuppressWarnings("deprecation")
public enum OrderEnum {
    Type((a, b) -> {
        Material aVal = a.item.getType();
        Material bVal = b.item.getType();
        
        return aVal.equals(bVal) ? 0 : compare(aVal.getId(), bVal.getId()); 
    }),
    Metadata((a, b) -> {
        int first = compare(a.item.hasItemMeta(), b.item.hasItemMeta());
        if (first != 0)
            return first;
        else
            return compare(
                    a.item.getItemMeta().getEnchants().keySet().size(),
                    b.item.getItemMeta().getEnchants().keySet().size());

    }), 
    Durability((a, b) -> compare(a.item.getDurability(), b.item.getDurability())),
    Amount((a, b) -> compare(a.item.getAmount(), b.item.getAmount())),
    Time((a, b) -> compare(a.placedTime, b.placedTime));

    private BinItemComparison comparision;

    private OrderEnum(BinItemComparison com) {
        comparision = com;
    }

    public BinItemComparison getComparision() {
        return comparision;
    }

    private static int compare(long aVal, long bVal) {
        if (aVal > bVal)
            return 1;
        else if (aVal < bVal)
            return -1;
        else
            return 0;
    }

    private static int compare(boolean aHas, boolean bHas) {
        if (aHas && bHas)
            return 0;
        else if (aHas)
            return 1;
        else if (bHas)
            return -1;
        else
            return 0;
    }
}
