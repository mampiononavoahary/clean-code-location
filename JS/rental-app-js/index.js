const readline = require('readline-sync');
const { getItems, findByName } = require('./services/itemService');
const { rentItem, getActiveRentals } = require('./services/rentalService');
const { parseDate, isValidRange } = require('./utils/dateUtils');

function main() {
  while (true) {
    console.log('\nWelcome to Rental App');
    console.log('1. See all items');
    console.log('2. Rent an item');
    console.log('3. See active rentals');
    console.log('4. Exit');
    const choice = readline.question('Choose an option: ');

    switch (choice) {
      case '1':
        showItems();
        break;
      case '2':
        rentItemFlow();
        break;
      case '3':
        showRentals();
        break;
      case '4':
        console.log('Goodbye!');
        return;
      default:
        console.log('Invalid option');
    }
  }
}

function showItems() {
  const items = getItems();
  console.log('\nAvailable items:');
  items.forEach((item, index) => {
    console.log(`${index + 1}. ${item.name}`);
  });
}

function rentItemFlow() {
  const itemName = readline.question('Enter item name: ');
  const item = findByName(itemName);
  if (!item) {
    console.log('Item not found.');
    return;
  }

  const start = parseDate(readline.question('Enter start date (YYYY-MM-DD): '));
  const end = parseDate(readline.question('Enter end date (YYYY-MM-DD): '));

  if (!isValidRange(start, end)) {
    console.log('Invalid date range (must be at least one day long)');
    return;
  }

  const result = rentItem(item, start, end);
  console.log(result.success ? 'Item rented successfully!' : result.message);
}

function showRentals() {
  const rentals = getActiveRentals();
  if (rentals.length === 0) {
    console.log('No active rentals.');
    return;
  }

  console.log('\nActive rentals:');
  rentals.forEach(r => {
    console.log(`${r.item.name} rented from ${r.startDate.toDateString()} to ${r.endDate.toDateString()}`);
  });
}

main();
