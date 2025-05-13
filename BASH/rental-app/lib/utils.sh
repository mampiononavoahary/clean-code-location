#!/bin/bash

valid_date() {
  date -d "$1" +%F &>/dev/null
}

date_is_before() {
  [[ $(date -d "$1" +%s) -lt $(date -d "$2" +%s) ]]
}

date_is_after() {
  [[ $(date -d "$1" +%s) -gt $(date -d "$2" +%s) ]]
}

rental_is_one_day_min() {
  start_epoch=$(date -d "$1" +%s)
  end_epoch=$(date -d "$2" +%s)
  diff_days=$(( (end_epoch - start_epoch) / 86400 ))
  [[ $diff_days -ge 1 ]]
}

rental_conflicts() {
  item="$1"
  new_start=$(date -d "$2" +%s)
  new_end=$(date -d "$3" +%s)

  while IFS="|" read -r i s e; do
    [[ "$i" != "$item" ]] && continue
    start=$(date -d "$s" +%s)
    end=$(date -d "$e" +%s)
    if [[ $new_start -lt $end && $new_end -gt $start ]]; then
      return 0
    fi
  done < data/rentals.txt
  return 1
}

get_conflict_info() {
  item="$1"
  while IFS="|" read -r i s e; do
    if [[ "$i" == "$item" ]]; then
      echo "❌ '$item' is already rented from $s to $e"
    fi
  done < data/rentals.txt
}
