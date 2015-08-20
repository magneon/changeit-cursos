<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Início ::</title>

<link rel="stylesheet" type="text/stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.3.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.mask.js' />"></script>
<script type="text/javascript">
	$(".maskCpf").mask('###.###.###-##', {reverse: true});
</script>
</head>
<body>

	<div class="container">

		<form action="<c:url value='/login/logar' />" class="form-horizontal" method="post">
		
			<div class="form-group">
				<div class="col-sm-offset-2">
					<h3 class="h3">Bem vindo ao Change IT Cursos!</h3>
				</div>
			</div>
		
			<div class="form-group">
				<label for="cpfAluno" class="control-label col-sm-2">CPF</label>
				<div class="col-sm-8">
					<input type="text" id="cpfAluno" class="form-control maskCpf" name="aluno.cpf" value="${aluno.cpf}" maxlength="14"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="senhaAluno" class="control-label col-sm-2">Senha</label>
				<div class="col-sm-8">
					<input type="password" id="senhaAluno" class="form-control" name="aluno.senha" value="${aluno.senha}"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2">
					<input type="submit" class="btn btn-primary" value="Login"/>
				</div>
			</div>
			
			<c:if test="${not empty mensagem}">
				<div class="form-group">
					<div class="bg-success">
						${mensagem} <br />
					</div>
				</div>
			</c:if>
			
			<c:if test="${not empty errors}">
				<div class="form-group">
					<div class="bg-danger">
						<c:forEach var="error" items="${errors}">
							${error.message} <br />
						</c:forEach>
					</div>
				</div>
			</c:if>
		
		</form>
	
	</div>

</body>
</html>