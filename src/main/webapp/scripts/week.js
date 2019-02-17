function getWeekDay(date) {
    var days = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday'];
    return days[date.getDay()];
}

function getMonthYear(date) {
    var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July',
    		'August', 'September', 'October', 'November', 'December'];
    return months[date.getMonth()];
}

function getSunday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 7));
};

function getMonday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 1));
};

function getTuesday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 2))
};

function getWednesday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 3))
};

function getThursday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 4))
};

function getFriday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 5))
};

function getSaturday() {
	return new Date((1900 + date.getYear()), date.getMonth(), (date.getDate() - date.getDay() + 6))
};