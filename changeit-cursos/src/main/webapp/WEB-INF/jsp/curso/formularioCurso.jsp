<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Novo Curso ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css' />">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.mask.js'/>"></script>
<script type="text/javascript">
	$(".course-value").mask('###.###,9#', {reverse: true});
</script>
</head>
<body>
	<div class="container">
				
		<form action="<c:url value='/curso/adicionar' />" class="form-horizontal" method="post">
			<div class="form-group">
				<div class="col-sm-offset-2">
					<h3 class="h3">Novo curso</h3>
				</div>
			</div>
		
			<input type="hidden" name="curso.idCurso" value="${curso.idCurso}"/>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="nomeCurso">Nome do Curso</label>
				<div class="col-sm-8">
					<input id="nomeCurso" name="curso.nomeCurso" value="${curso.nomeCurso}" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="descricaoCurso">Descrição do Curso</label>
				<div class="col-sm-8">
					<input id="descricaoCurso" name="curso.descricaoCurso" value="${curso.descricaoCurso}" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="valorCurso">Valor do Curso</label>
				<div class="col-sm-8">
					<input id="valorCurso" name="curso.valorCurso" value="${curso.valorCurso}" class="form-control course-value" maxlength="7"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group col-sm-offset-8">
					<a href="<c:url value='/curso/lista' />" class="btn btn-primary">Cancelar</a>
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