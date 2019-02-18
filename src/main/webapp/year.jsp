<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar month</title>
<link href="css/year.css" rel="stylesheet">
</head>
<body>
	<div id="main">
	<main> 
	<input id="tab1" type="radio" name="tabs" checked>
	<label for="tab1" title="Толстым шрифтом отмечены дни проведенные с пользой">Useful day</label> 
	<input id="tab2" type="radio" name="tabs"> 
	<label for="tab2" title="Толстым шрифтом отмечены дни в которые вы работали">Work</label> 
	<input id="tab3" type="radio" name="tabs"> 
	<label for="tab3" title="Толстым шрифтом отмечены дни в которые вы учились">Study</label> 
	<input id="tab4" type="radio" name="tabs"> 
	<label for="tab4" title="Толстым шрифтом отмечены дни в которые вы практиковались в иностранных языках">Learg Language</label>
	<input id="tab5" type="radio" name="tabs"> 
	<label for="tab5" title="Толстым шрифтом отмечены дни в которые вы занимались спортом">Sport</label>
	<input id="tab6" type="radio" name="tabs"> 
	<label for="tab6" title="Толстым шрифтом отмечены дни в которые вы употребляли алкоголь">Alcohol</label>
	<input id="tab7" type="radio" name="tabs"> 
	<label for="tab7" title="Толстым шрифтом отмечены дни в которые вы курили">Smoke</label>

	<section id="content1">
		<p><% out.println((String) request.getAttribute("year_useful")); %></p>
	</section>

	<section id="content2">
		<p><% out.println((String) request.getAttribute("year_work")); %></p>
	</section>

	<section id="content3">
		<p><% out.println((String) request.getAttribute("year_study")); %></p>
	</section>

	<section id="content4">
		<p><% out.println((String) request.getAttribute("year_learn_language")); %></p>
	</section>
	
	<section id="content5">
		<p><% out.println((String) request.getAttribute("year_sport")); %></p>
	</section>
	
	<section id="content6">
		<p><% out.println((String) request.getAttribute("year_alcohol")); %></p>
	</section>
	
	<section id="content7">
		<p><% out.println((String) request.getAttribute("year_smoke")); %></p>
	</section>

	</main>
		</div>
	
	<div class="side_bar">
	 <main>
	 	<table class="side_bar">
				<tr class="side_bar">
					<td class="side_bar">
						MENU
					</td>
				</tr>
				<tr class="side_bar">
					<td class="side_bar">
						<a href="index.html">Change user</a>
					</td>
				</tr>
				<tr class="side_bar">
					<td class="side_bar">
						<a href="week.html">Set this week</a>
					</td>
				</tr>
				<tr class="side_bar">
					<td class="side_bar">
						<a href="MonthPrep">Show this month</a>
					</td>
				</tr>
				<tr class="side_bar">
					<td class="side_bar">
						<a href="YearPrep">Show this year</a>
					</td>
				</tr>
				<tr class="side_bar">
					<td class="side_bar">
						<a href="info.html">Information</a>
					</td>
				</tr>
			</table>
	 </main>
	</div>
</body>
</html>