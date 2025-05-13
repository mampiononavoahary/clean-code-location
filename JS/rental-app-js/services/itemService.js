const Item = require('../models/Item');

const items = [
  new Item('Bike'),
  new Item('Camera'),
  new Item('Tent')
];

function getItems() {
  return items;
}

function findByName(name) {
  return items.find(item => item.name.toLowerCase() === name.toLowerCase());
}

module.exports = { getItems, findByName };
