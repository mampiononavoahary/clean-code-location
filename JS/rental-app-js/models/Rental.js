class Rental {
    constructor(item, startDate, endDate) {
      this.item = item;
      this.startDate = new Date(startDate);
      this.endDate = new Date(endDate);
    }
  
    isActive() {
      return this.endDate >= new Date();
    }
  }
  
  module.exports = Rental;
  