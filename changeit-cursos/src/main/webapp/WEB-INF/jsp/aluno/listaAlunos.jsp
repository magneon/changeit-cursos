<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Lista de Alunos ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css'/>">
</head>
<body>
	
	<div class="container">
		<h3 class="h3">Lista de Alunos</h3>
		<hr />
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>CPF</th>
					<th>Nome</th>
					<th>Idade</th>
					<th>Turma</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="aluno" items="${alunos}">
					<tr>
						<td>${aluno.cpf}</td>
						<td>${aluno.nomeAluno}</td>
						<td>${aluno.idadeAluno}</td>
						<td>${aluno.turma.nomeTurma}</td>
						<td>
							<a href="<c:url value='/aluno/novo?aluno.cpf=${aluno.cpf}'/>">Alterar</a>
							| 
							<a href="<c:url value='/aluno/remover?aluno.cpf=${aluno.cpf}'/>">Excluir</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="col-sm-offset-10">
			<a class="btn btn-primary" href="<c:url value='/turma/lista'/>">Voltar</a>
			<a class="btn btn-primary" href="<c:url value='/aluno/novo'/>">Adicionar</a>
		</div>
		
		<br />
		
		<c:if test="${not empty mensagem}">
			<div class="bg-success">
				${mensagem}
			</div>
		</c:if>
	</div>

</body>
</html>