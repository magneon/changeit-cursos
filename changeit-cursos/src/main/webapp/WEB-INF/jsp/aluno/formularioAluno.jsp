<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Novo Aluno ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.3.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.mask.js' />"></script>
<script type="text/javascript">
	$(".maskCpf").mask('###.###.###-##', {reverse: true});
	
// 	function getCpfAlunoValue() {
// 		$("#cpfAlunoHidden").val(
// 			$('.maskCpf').val().replace(/\./gi,"").replace(/\-/gi, "")
// 		);
// 	}	
	
</script>
</head>
<body>
	
	<div class="container">
	
		<form action="<c:url value='/aluno/adicionar'/>" method="post" class="form-horizontal">
		
			<div class="form-group">
				<div class="col-sm-offset-2">
					<h3 class="h3">Novo aluno</h3>
				</div>
			</div>
		
			<div class="form-group">
				<label for="cpfAluno" class="control-label col-sm-2">CPF</label>
				<div class="col-sm-8">
					<input class="form-control maskCpf" id="cpfAluno" name="aluno.cpf" value="${aluno.cpf}" maxlength="14" />
				</div>
			</div>
			<div class="form-group">
				<label for="senhaAluno" class="control-label col-sm-2">Senha</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="senhaAluno" name="aluno.senha" value="${aluno.senha}" maxlength="8" />
				</div>
			</div>
			<div class="form-group">
				<label for="nomeAluno" class="control-label col-sm-2">Nome do Aluno</label>
				<div class="col-sm-8">
					<input class="form-control" id="nomeAluno" name="aluno.nomeAluno" value="${aluno.nomeAluno}"/>
				</div>
			</div>
			<div class="form-group">
				<label for="idadeAluno" class="control-label col-sm-2">Idade do Aluno</label>
				<div class="col-sm-8">
					<input class="form-control" id="idadeAluno" name="aluno.idadeAluno" value="${aluno.idadeAluno}"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-8">
					<a class="btn btn-primary" href="<c:url value='/aluno/lista'/>">Cancelar</a>
					<input type="submit" value="Adicionar" class="btn btn-primary"/>
				</div>
			</div>		
		</form>
		
		
		<br />
		
		<c:if test="${not empty errors}">
			<div class="bg-danger">
				<c:forEach var="error" items="${errors}">
					${error.message} <br/>
				</c:forEach>
			</div>
		</c:if>
		
		<c:if test="${not empty error}">
			<div class="bg-danger">
				${error}
			</div>
		</c:if>
	
	</div>
	
</body>
</html>