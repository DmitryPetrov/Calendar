var date = new Date(); 
		
var monday = getMonday();
var tuesday = getTuesday();
var wednesday = getWednesday();
var thursday = getThursday();
var friday = getFriday();
var saturday = getSaturday();
var sunday = getSunday();

function getWeekDay(date) {
    var days = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday'];
    return days[date.getDay()];
}

function getMonthYear(date) {
    var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July',
    		'August', 'September', 'October', 'November', 'December'];
    return months[date.getMonth()];
}

function getMonday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 1));
};

function getTuesday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 2))
};

function getWednesday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 3))
};

function getThursday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 4))
};

function getFriday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 5))
};

function getSaturday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 6))
};

function getSunday() {
	dayOfWeek = correctionToRussianWeek();
	return new Date((1900 + date.getYear()), date.getMonth(), (dayOfWeek + 7));
};

function correctionToRussianWeek() {
	if (date.getDay() == 0) {
		dayOfWeek = (date.getDate() - date.getDay() - 7);
	} else {
		dayOfWeek = (date.getDate() - date.getDay());
	}
	return dayOfWeek;
}
 
function printDayOfWeek(dayOfWeekEng, dayOfWeekRus) {
	if (getWeekDay(date) === dayOfWeekEng) {
		document.write('<strong>▼</strong><br>');
	}
	document.write(dayOfWeekRus + ' (' + eval(dayOfWeekEng).getDate() + ')');	 
}
