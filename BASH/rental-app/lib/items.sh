#!/bin/bash

list_items() {
  echo ""
  echo "Items available for rent:"
  cat data/items.txt | nl
}
