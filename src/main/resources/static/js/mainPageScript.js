function getDayOfWeekName(dayInput) {
    date = new Date()
    const day = date.getDay();
    date.setDate(date.getDate() - day + (day == 0 ? -6:dayInput));
    return date.toLocaleDateString('default', {weekday: 'long',});
}

function getNumberOfWeekName(dayInput) {
    date = new Date()
    const day = date.getDay();
    date.setDate(date.getDate() - day + (day == 0 ? -6:dayInput));
    return date.toLocaleDateString('default', {day: 'numeric'});
}