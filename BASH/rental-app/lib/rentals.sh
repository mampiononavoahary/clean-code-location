#!/bin/bash
source lib/utils.sh

rent_item() {
  list_items
  read -rp "Enter the item name: " item
  if ! grep -qxF "$item" data/items.txt; then
    echo "Item does not exist."
    return
  fi

  read -rp "Enter start date (YYYY-MM-DD): " start_date
  read -rp "Enter end date (YYYY-MM-DD): " end_date

  if ! valid_date "$start_date" || ! valid_date "$end_date"; then
    echo "Invalid date format."
    return
  fi

  if ! date_is_before "$start_date" "$end_date"; then
    echo "End date must be after start date."
    return
  fi

  if ! rental_is_one_day_min "$start_date" "$end_date"; then
    echo "Rental must be at least one day long."
    return
  fi

  if rental_conflicts "$item" "$start_date" "$end_date"; then
    get_conflict_info "$item" "$start_date" "$end_date"
    return
  fi

  echo "$item|$start_date|$end_date" >> data/rentals.txt
  echo "Rental confirmed!"
}

list_active_rentals() {
  today=$(date +%F)
  echo ""
  echo "Active Rentals:"
  while IFS="|" read -r item start end; do
    if date_is_after "$end" "$today"; then
      echo "- $item: $start to $end"
    fi
  done < data/rentals.txt
}
