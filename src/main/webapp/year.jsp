<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<label for="tab1">Useful day</label> 
	<input id="tab2" type="radio" name="tabs"> 
	<label for="tab2">Work</label> 
	<input id="tab3" type="radio" name="tabs"> 
	<label for="tab3">Study</label> 
	<input id="tab4" type="radio" name="tabs"> 
	<label for="tab4">Learg Language</label>
	<input id="tab5" type="radio" name="tabs"> 
	<label for="tab5">Sport</label>
	<input id="tab6" type="radio" name="tabs"> 
	<label for="tab6">Alcohol</label>
	<input id="tab7" type="radio" name="tabs"> 
	<label for="tab7">Smoke</label>

	<section id="content1">
		<p><% out.println((String) getServletContext().getAttribute("year_useful")); %></p>
	</section>

	<section id="content2">
		<p><% out.println((String) getServletContext().getAttribute("year_work")); %></p>
	</section>

	<section id="content3">
		<p><% out.println((String) getServletContext().getAttribute("year_study")); %></p>
	</section>

	<section id="content4">
		<p><% out.println((String) getServletContext().getAttribute("year_learn_language")); %></p>
	</section>
	
	<section id="content5">
		<p><% out.println((String) getServletContext().getAttribute("year_sport")); %></p>
	</section>
	
	<section id="content6">
		<p><% out.println((String) getServletContext().getAttribute("year_alcohol")); %></p>
	</section>
	
	<section id="content7">
		<p><% out.println((String) getServletContext().getAttribute("year_smoke")); %></p>
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
						<a href="month.jsp">Show this month</a>
					</td>
				</tr>
				<tr class="side_bar">
					<td class="side_bar">
						<a href="year.jsp">Show this year</a>
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