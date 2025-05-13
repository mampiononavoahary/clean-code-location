const rentals = [];

function rentItem(item, startDate, endDate) {
  for (const r of rentals) {
    if (r.item.name === item.name &&
        !(endDate < r.startDate || startDate > r.endDate)) {
      return {
        success: false,
        message: `Item already rented from ${r.startDate.toDateString()} to ${r.endDate.toDateString()}`
      };
    }
  }

  rentals.push({ item, startDate, endDate });
  return { success: true };
}

function getActiveRentals() {
  return rentals.filter(r => r.endDate >= new Date());
}

module.exports = { rentItem, getActiveRentals };
