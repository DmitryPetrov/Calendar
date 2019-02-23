<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Calendar week</title>
	<link href="css/week.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
 	<script src="js/week.js"></script>
</head>

<body>	
<div id="main">
	<main>
	<table class="outer_table">
		<tr><th class="outer_table" colspan="7">
			<script>document.write(getMonthYear(date) + ' ' + (1900 + date.getYear()));</script></th></tr>

		<tr>
			<td align="center" class="table_hat">
				<script>printDayOfWeek('monday', 'ПН');</script></td>
			    
			<td align="center" class="table_hat">
				<script>printDayOfWeek('tuesday', 'ВТ');</script></td>
			    
			<td align="center" class="table_hat">
				<script>printDayOfWeek('wednesday', 'СР');</script></td>
			    
			<td align="center" class="table_hat">
				<script>printDayOfWeek('thursday', 'ЧТ');</script></td>
			    
			<td align="center" class="table_hat">
				<script>printDayOfWeek('friday', 'ПТ');</script></td>
			    
			<td align="center" class="table_hat">
				<script>printDayOfWeek('saturday', 'СБ');</script></td>
			   
			<td align="center" class="table_hat">
				<script>printDayOfWeek('sunday', 'ВС');</script></td>
		</tr>
		
		<tr id='second_font'>
			<td><form class="sendForm" id="1">
				<p><% out.println((String) request.getAttribute("week_monday")); %></p>
				<div id="result"></div>
				</form></td>
				
			<td><form class="sendForm" id="2">
				<p><% out.println((String) request.getAttribute("week_tuesday")); %></p>
				<div id="result"> </div>
				</form></td>	
				
			<td><form class="sendForm" id="3">
				<p><% out.println((String) request.getAttribute("week_wednesday")); %></p>
				<div id="result"> </div>
				</form></td>	
				
			<td><form class="sendForm" id="4">
				<p><% out.println((String) request.getAttribute("week_thursday")); %></p>
				<div id="result"> </div>
				</form></td>	
				
			<td><form class="sendForm" id="5">
				<p><% out.println((String) request.getAttribute("week_friday")); %></p>
				<div id="result"> </div>
				</form></td>		
							
			<td><form class="sendForm" id="6">
				<p><% out.println((String) request.getAttribute("week_saturday")); %></p>
				<div id="result"> </div>
				</form></td>	
				
			<td><form class="sendForm" id="0">
				<p><% out.println((String) request.getAttribute("week_sunday")); %></p>
				<div id="result"> </div>
				</form></td>		
	</table>
		<script>
 			$(".sendForm").submit(function(event) {
				var formID = $(this).attr("id"); // Получение ID формы на которой была нажата кнопка
	            var formNm = $('#' + formID); //получение ссылки на форму

				$.ajax({
					url: '/Calendar/AddDay',
					type: 'post',
					dataType: 'html',
					data: formNm.serialize(),//собираем данные с формы
					success: function(response) { //обработать результат 
						$(formNm).find("div").html("Ok"); //по ссылке с формой находим тег div и вставляем туда ответ
					}
				});
				return false;
			});
		</script>
	</main>
	</div>
	
	<div class="side_bar">
	<main>
		<table class="side_bar">
				<tr class="side_bar">
					<td class="side_bar">MENU</td></tr>
					
				<tr class="side_bar">
					<td class="side_bar">
						<a href="index.html">Change user</a></td></tr>
						
				<tr class="side_bar">
					<td class="side_bar">
						<a href="WeekPrep">Set this week</a></td></tr>
						
				<tr class="side_bar">
					<td class="side_bar">
						<a href="MonthPrep">Show this month</a></td></tr>
						
				<tr class="side_bar">
					<td class="side_bar">
						<a href="YearPrep">Show this year</a></td></tr>
						
				<tr class="side_bar">
					<td class="side_bar">
						<a href="info.html">Information</a></td></tr>						
		</table>
	</main>
	</div>
	
</body>
</html>