<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Lista de Cursos ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css'/>">
</head>
<body>
	<div class="container">
		<h3 class="h3">Listagem de Cursos</h3>
		
		<table class="table table-bordered">
			<thead>
				<tr align="center">
					<th>Nome do Curso</th>
					<th>Descrição do Curso</th>
					<th>Valor do Curso</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="curso" items="${cursos}">
					<tr>
						<td>${curso.nomeCurso}</td>
						<td>${curso.descricaoCurso}</td>
						<td>
							<fmt:formatNumber type="currency" maxFractionDigits="2" value="${curso.valorCurso}" />
						</td>
						<td>
							<a href="<c:url value='/curso/novo?curso.idCurso=${curso.idCurso}'/>">Alterar</a>
							 | 
							<a href="<c:url value='/curso/remover?curso.idCurso=${curso.idCurso}'/>">Remover</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br/>
		
		<div class="col-sm-offset-10">
			<a class="btn btn-primary" href="<c:url value='/turma/lista' />">Voltar</a>
			<a class="btn btn-primary" href="<c:url value='/curso/novo' />">Adicionar</a>
		</div>

		<br />
		
		<div class="row">
			<c:if test="${not empty mensagem}">
				<div class="bg-success col-sm-10">
					${mensagem}
				</div>
			</c:if>
			
			<c:if test="${not empty errors}">
				<c:forEach var="error col-sm-10" items="${errors}">
					<div class="bg-danger">
						${error.message}
					</div>
				</c:forEach>
			</c:if>
		</div>
		
	</div>

</body>
</html>