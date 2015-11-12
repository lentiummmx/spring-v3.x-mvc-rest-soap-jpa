<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../partials/taglib.jsp" %>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><sitemesh:write property='title'/></title>
		<%@include file="../partials/style-js.jsp" %>
		<sitemesh:write property='head'/>
	</head>
	<body>
	
		<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="">ISS</a>
				</div>
			</div> <!-- /.container -->
		</div> <!-- /.navbar -->
	
		<div class="container">
			<div class="row">
				<div class="span12">
					<sitemesh:write property='body' />
				</div>
			</div>
		</div>
	
	</body>
</html>