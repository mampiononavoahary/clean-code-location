package services;

import models.Item;

import java.util.Arrays;
import java.util.List;

public class ItemService {
    private final List<Item> items = Arrays.asList(
            new Item("Bike"),
            new Item("Camera"),
            new Item("Tent")
    );

    public List<Item> getItems() {
        return items;
    }

    public Item findByName(String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
