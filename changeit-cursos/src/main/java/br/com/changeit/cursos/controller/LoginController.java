package br.com.changeit.cursos.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.changeit.cursos.annotations.Logar;
import br.com.changeit.cursos.dao.AlunoDAO;
import br.com.changeit.cursos.model.Aluno;
import br.com.changeit.cursos.model.AlunoLogado;

@Controller
public class LoginController {
	
	private final AlunoDAO alunoDAO;
	private final Result result;
	private final Validator validator;
	private final AlunoLogado alunoLogado;

	@Deprecated
	public LoginController() {
		this(null, null, null, null);
	}
	
	@Inject
	public LoginController(AlunoDAO alunoDAO, Result result, Validator validator, AlunoLogado alunoLogado) {
		this.alunoDAO = alunoDAO;
		this.result = result;
		this.validator = validator;
		this.alunoLogado = alunoLogado;
	}
	
	@Logar
	@Path("/")
	public void inicio() {}
	
	@Logar
	@Post("/login/logar")
	public void logar(Aluno aluno) {
		Aluno alunoFindable = alunoDAO.buscaPorCPFESenha(aluno);
		
		validator.check(alunoFindable != null, new I18nMessage("login", "modelo.aluno.login.invalido"));
		validator.onErrorUsePageOf(LoginController.class).inicio();
		
		alunoLogado.setAluno(alunoFindable);
		
		result.redirectTo(TurmaController.class).listaTurmas();
		
	}
	
	@Get("/login/deslogar")
	public void deslogar(Aluno aluno) {
		alunoLogado.setAluno(null);
		
		result.include("mensagem", "Aluno deslogado!");
		result.redirectTo(LoginController.class).inicio();
	}

}
