<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar month</title>
<link href="css/month.css" rel="stylesheet">
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
		<p><% out.println((String) getServletContext().getAttribute("useful")); %></p>
	</section>

	<section id="content2">
		<p><% out.println((String) getServletContext().getAttribute("work")); %></p>
	</section>

	<section id="content3">
		<p><% out.println((String) getServletContext().getAttribute("study")); %></p>
	</section>

	<section id="content4">
		<p><% out.println((String) getServletContext().getAttribute("learn_language")); %></p>
	</section>
	
	<section id="content5">
		<p><% out.println((String) getServletContext().getAttribute("sport")); %></p>
	</section>
	
	<section id="content6">
		<p><% out.println((String) getServletContext().getAttribute("alcohol")); %></p>
	</section>
	
	<section id="content7">
		<p><% out.println((String) getServletContext().getAttribute("smoke")); %></p>
	</section>

	</main>
		</div>
	
	<div class="side_bar">
	 <main>
	 	<table>
				<tr>
					<td class="side_bar">
						MENU
					</td>
				</tr>
				<tr>
					<td class="side_bar">
						<a href="index.html">Change user</a>
					</td>
				</tr>
				<tr>
					<td class="side_bar">
						<a href="week.html">Set this week</a>
					</td>
				</tr>
				<tr>
					<td class="side_bar">
						<a href="info.html">Information</a>
					</td>
				</tr>
			</table>
	 </main>
	</div>
</body>
</html>