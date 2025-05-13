package services;

import models.Item;
import models.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentalService {
    private final List<Rental> rentals = new ArrayList<>();

    public String rentItem(Item item, LocalDate start, LocalDate end) {
        for (Rental rental : rentals) {
            if (rental.getItem().getName().equalsIgnoreCase(item.getName()) &&
                    !(end.isBefore(rental.getStartDate()) || start.isAfter(rental.getEndDate()))) {
                return "Item already rented from " + rental.getStartDate() + " to " + rental.getEndDate();
            }
        }

        rentals.add(new Rental(item, start, end));
        return "Item rented successfully!";
    }

    public List<Rental> getActiveRentals() {
        return rentals.stream()
                .filter(Rental::isActive)
                .collect(Collectors.toList());
    }
}
