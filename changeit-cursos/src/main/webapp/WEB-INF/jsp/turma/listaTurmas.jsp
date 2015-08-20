<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Turmas ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css' />">
</head>
<body>

	<div class="container">
		
		<h3 class="h3">Lista de Turmas</h3>
	
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>ID</td>
					<td>Nome da Turma</td>
					<td>Curso</td>
					<td>Ações</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="turma" items="${turmas}">
					<tr>
						<td>${turma.idTurma}</td>
						<td>${turma.nomeTurma}</td>
						<td>${turma.curso.nomeCurso}</td>
						<td>
							<a href="<c:url value='/turma/remover?turma.idTurma=${turma.idTurma}'/>">Excluir Turma</a> |
							<a href="<c:url value='/turma/listaAlunos?turma.idTurma=${turma.idTurma}'/>">Adicionar Alunos</a> |
							<a href="<c:url value='/turma/removerAlunosDaTurma?turma.idTurma=${turma.idTurma}'/>">Remover Alunos</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="col-sm-offset-8">
			<a class="btn btn-danger" href="<c:url value='/login/deslogar'/>">Sair</a>
			<a class="btn btn-primary" href="<c:url value='/turma/novo'/>">Criar Turma</a>
			<a class="btn btn-primary" href="<c:url value='/aluno/lista'/>">Alunos</a>
			<a class="btn btn-primary" href="<c:url value='/curso/lista'/>">Cursos</a>
		</div>
		
		<c:if test="${not empty mensagem}">
			<div class="bg-success">
				${mensagem}
			</div>
		</c:if>
	
	</div>

</body>
</html>