package br.com.changeit.cursos.controller;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.changeit.base.ValidateCPFUtil;
import br.com.changeit.cursos.annotations.Interceptar;
import br.com.changeit.cursos.dao.AlunoDAO;
import br.com.changeit.cursos.model.Aluno;

@Controller
public class AlunoController {
	
	/** The aluno dao. */
	private final AlunoDAO alunoDAO;
	
	/** The result. */
	private final Result result;

	/** The validator. */
	private Validator validator;

	/**
	 * Instantiates a new aluno controller.
	 */
	@Deprecated
	public AlunoController() {
		this(null, null, null);
	}
	
	/**
	 * Instantiates a new aluno controller.
	 *
	 * @param alunoDAO the aluno dao
	 */
	@Inject
	public AlunoController(AlunoDAO alunoDAO, Result result, Validator validator) {
		this.alunoDAO = alunoDAO;
		this.result = result;
		this.validator = validator;
	}
	
	@Get("/aluno/novo")
	public void formularioAluno(Aluno aluno) {
		if (aluno.getCpf() != null) {
			result.include("aluno", alunoDAO.buscaPorCPF(aluno));
		}
	}

	/**
	 * Lista alunos.
	 */
	@Get("/aluno/lista")
	public void listaAlunos() {
		result.include("alunos", this.alunoDAO.listaAlunos());
	}
	
	/**
	 * Adicionar.
	 *
	 * @param aluno the aluno
	 */
	@Post
	@Interceptar
	public void adicionar(Aluno aluno) {
		
		validarDadosAluno(aluno);
		
		try {
			if (aluno.getCpf() != null) {
				alterar(aluno);
			} else {
				alunoDAO.adicionar(aluno);
				result.include("mensagem", "Aluno adicionado com sucesso!");
				result.redirectTo(this).listaAlunos();
			}
		} catch (EntityExistsException entityExistsException) {
			result.include("error", "CPF já cadastrado no banco de dados!");
			result.redirectTo(this).formularioAluno(aluno);
		} catch (IllegalArgumentException illegalArgumentException) {
			result.include("error", "Objeto informado não é uma entidade!");
			result.redirectTo(this).formularioAluno(aluno);
		} catch (Exception exception) {
			result.include("error", "Erro desconhecido!");
			result.redirectTo(this).formularioAluno(aluno);
		}
		
	}
	
	@Post("/aluno/alterar")
	@Interceptar
	public void alterar(Aluno aluno) {
		validarDadosAluno(aluno);
		
		alunoDAO.alterar(aluno);
		result.include("mensagem", "Aluno alterado com sucesso!");
		result.redirectTo(this).listaAlunos();
	}
	
	@Get
	@Interceptar
	public void remover(Aluno aluno) {
		alunoDAO.remover(aluno);
		result.include("mensagem", "Aluno removido com sucesso!");
		result.redirectTo(this).listaAlunos();
	}

	/**
	 * Validar dados aluno.
	 *
	 * @param aluno the aluno
	 */
	private void validarDadosAluno(Aluno aluno) {
		validator.check(aluno.getCpf() != null && ValidateCPFUtil.validateCPF(aluno.getCpf()), new I18nMessage("aluno", "modelo.aluno.cpf.invalido"));
		validator.check(aluno.getNomeAluno() != null && aluno.getNomeAluno().length() > 5, new I18nMessage("aluno", "modelo.aluno.nome.invalido"));
		validator.check(aluno.getSenha() != null && aluno.getSenha().length() == 8, new I18nMessage("aluno", "modelo.aluno.senha.invalido"));
		validator.check(aluno.getIdadeAluno() != null && aluno.getIdadeAluno() > 0, new I18nMessage("aluno", "modelo.aluno.idade.invalido"));
		validator.onErrorUsePageOf(this).formularioAluno(aluno);
	}

}
