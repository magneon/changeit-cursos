<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Nova Turma ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.3.min.js'/>"></script>
<script type="text/javascript">
	$.get("<c:url value='/curso/listaJSON'/>", function(resultado) {
		
		var options = [];
		
		options.push("<option value='0'> -- Selecione -- </option>");
		
		for (var i = 0; i < resultado.list.length; i++) {
			
			var obj = resultado.list[i];
			
			options.push('<option value="' + obj.idCurso + '">' + obj.nomeCurso + '</option>');
						
		}
		
		$("#cursos").html(options.join(''));			
		
	});
</script>
</head>
<body>
	
	<div class="container">
	
		<form action="<c:url value='/turma/adicionar'/>" class="form-horizontal" method="post">

			<div class="form-group">
				<div class="col-sm-offset-2">
					<h3 class="h3">Adicionar nova turma</h3>
				</div>
			</div>

			<div class="form-group">
				<label for="nomeTurma" class="col-sm-2 control-label">Nome da Turma</label>
				<div class="col-sm-8">
					<input class="form-control" id="nomeTurma" name="turma.nomeTurma" value="${turma.nomeTurma}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="cursoTurma" class="col-sm-2 control-label">Curso</label>
				<div class="col-sm-8">
					<select id="cursos" name="turma.curso.idCurso"></select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-8">
					<a class="btn btn-primary" href="<c:url value='/turma/lista'/>">Cancelar</a>
					<input type="submit" class="btn btn-primary" value="Adicionar"/>
				</div>
			</div>
		
		</form>
		
		<c:if test="${not empty errors}">
			<div class="bg-danger">
				<c:forEach var="error" items="${errors}">
					${error.message} <br/>
				</c:forEach>
			</div>
		</c:if>
	
	</div>

</body>
</html>