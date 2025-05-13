package models;

import java.time.LocalDate;

public class Rental {
    private Item item;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental(Item item, LocalDate startDate, LocalDate endDate) {
        this.item = item;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Item getItem() {
        return item;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return endDate.isAfter(LocalDate.now());
    }
}
