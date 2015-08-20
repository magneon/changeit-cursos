<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Alunos  ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.3.min.js' />"></script>
<script type="text/javascript">
	function adicionarAluno(cpf, idTurma) {
		$.post("<c:url value='/turma/adicionarAluno?aluno.cpf=" + cpf + "&turma.idTurma=" + idTurma + "'/>", function() {
			alert(cpf + " adicionado com sucesso!");
		});
	}
</script>
</head>
<body>
	
	<div class="container">
	
		<h3 class="h3">Adicionar alunos para o curso de ${turma.curso.nomeCurso}</h3>
	
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>CPF do Aluno</th>
					<th>Nome do Aluno</th>
					<th>Idade do Aluno</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="aluno" items="${alunos}">
					<tr>
						<td>${aluno.cpf}</td>
						<td>${aluno.nomeAluno}</td>
						<td>${aluno.idadeAluno}</td>
						<td>
							<input type="button" class="btn btn-primary" onclick="adicionarAluno('${aluno.cpf}', ${turma.idTurma});" value="Adicionar ao Curso">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form action="<c:url value='/turma/finalizar'/>" class="form-horizontal" method="post">
			<div class="form-group">
				<input type="submit" value="Finalizar" class="btn btn-primary"/>
			</div>
		</form>	
	</div>

</body>
</html>