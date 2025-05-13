function parseDate(dateStr) {
    const date = new Date(dateStr);
    return isNaN(date.getTime()) ? null : date;
  }
  
  function isValidRange(start, end) {
    if (!start || !end) return false;
    const oneDay = 24 * 60 * 60 * 1000;
    return end - start >= oneDay;
  }
  
  module.exports = { parseDate, isValidRange };
  