import models.Item;
import services.ItemService;
import services.RentalService;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ItemService itemService = new ItemService();
    private static final RentalService rentalService = new RentalService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to Rental App");
            System.out.println("1. See all items");
            System.out.println("2. Rent an item");
            System.out.println("3. See active rentals");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showItems();
                    break;
                case "2":
                    rentItem();
                    break;
                case "3":
                    showActiveRentals();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void showItems() {
        List<Item> items = itemService.getItems();
        System.out.println("Items available for rent:");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, items.get(i).getName());
        }
    }

    private static void rentItem() {
        System.out.print("Enter the item name: ");
        String itemName = scanner.nextLine();
        Item item = itemService.findByName(itemName);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate start = DateUtils.parseDate(scanner.nextLine());
        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate end = DateUtils.parseDate(scanner.nextLine());

        if (!DateUtils.isValidRange(start, end)) {
            System.out.println("Invalid date range. End date must be at least one day after start date.");
            return;
        }

        String result = rentalService.rentItem(item, start, end);
        System.out.println(result);
    }

    private static void showActiveRentals() {
        List<models.Rental> rentals = rentalService.getActiveRentals();
        if (rentals.isEmpty()) {
            System.out.println("No active rentals.");
        } else {
            System.out.println("Active rentals:");
            for (models.Rental rental : rentals) {
                System.out.printf("%s rented from %s to %s%n",
                        rental.getItem().getName(),
                        rental.getStartDate(),
                        rental.getEndDate());
            }
        }
    }
}
