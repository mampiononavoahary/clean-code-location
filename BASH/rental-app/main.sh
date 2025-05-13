#!/bin/bash
source lib/items.sh
source lib/rentals.sh
source lib/utils.sh

while true; do
  echo ""
  echo "Welcome to Rental App"
  echo "1. See all items"
  echo "2. Rent an item"
  echo "3. See active rentals"
  echo "4. Exit"
  read -rp "Choose an option: " choice

  case $choice in
    1) list_items ;;
    2) rent_item ;;
    3) list_active_rentals ;;
    4) echo "Exiting app. Goodbye!"; exit 0 ;;
    *) echo "Invalid option. Please try again." ;;
  esac
done
