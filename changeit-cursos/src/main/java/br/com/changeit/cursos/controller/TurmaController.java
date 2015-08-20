package br.com.changeit.cursos.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.changeit.cursos.annotations.Interceptar;
import br.com.changeit.cursos.dao.AlunoDAO;
import br.com.changeit.cursos.dao.TurmaDAO;
import br.com.changeit.cursos.model.Aluno;
import br.com.changeit.cursos.model.Turma;

/**
 * The Class TurmaController.
 */
@Controller
public class TurmaController {
	
	/** The turma dao. */
	private final TurmaDAO turmaDAO;
	
	/** The result. */
	private final Result result;

	/** The validator. */
	private final Validator validator;

	/** The aluno dao. */
	private final AlunoDAO alunoDAO;

	/**
	 * Instantiates a new turma controller.
	 */
	@Deprecated
	public TurmaController() {
		this(null, null, null, null);
	}

	/**
	 * Instantiates a new turma controller.
	 *
	 * @param turmaDAO the turma dao
	 * @param result the result
	 */
	@Inject
	public TurmaController(TurmaDAO turmaDAO, AlunoDAO alunoDAO, Result result, Validator validator) {
		this.turmaDAO = turmaDAO;
		this.alunoDAO = alunoDAO;
		this.result = result;
		this.validator = validator;
	}
	
	/**
	 * Lista turmas.
	 */
	@Get("/turma/lista")
	public void listaTurmas() {
		this.result.include("turmas", this.turmaDAO.listaTurmas());
	}
	
	/**
	 * Formulario turma.
	 */
	@Get("/turma/novo")
	public void formularioTurma() {
		
	}
	
	/**
	 * Adicionar turma.
	 *
	 * @param turma the turma
	 */
	@Interceptar
	@Post("/turma/adicionar")
	public void adicionarTurma(Turma turma) {
		
		validarDadosTurma(turma);
		
		turmaDAO.adicionarTurma(turma);
		result.include("mensagem", "Turma criada com sucesso!");
		result.redirectTo(this).listaTurmas();
	}

	@Interceptar
	@Get("/turma/remover")
	public void remover(Turma turma) {
		turmaDAO.removerTurma(turma);
		result.include("mensagem", "Turma removida com sucesso!");
		result.redirectTo(this).listaTurmas();
	}
	
	@Get("/turma/listaAlunos")
	public void listaAlunosParaTurma(Turma turma) {
		result.include("turma", turmaDAO.buscaTurma(turma));
		result.include("alunos", alunoDAO.listaAlunosSemTurma());
	}
	
	@Interceptar
	@Post("/turma/adicionarAluno")
	public void adicionarAluno(Aluno aluno, Turma turma) {
		Turma turmaFindable = turmaDAO.buscaTurma(turma);
		Aluno alunoFindable = alunoDAO.buscaPorCPF(aluno);
		
		alunoFindable.setTurma(turmaFindable);
		alunoDAO.alterar(alunoFindable);
		
		turmaFindable.getListaAlunos().add(alunoFindable);
		turmaDAO.atualizarTurma(turmaFindable);

		result.redirectTo(this).listaTurmas();
	}
	
	@Post("/turma/finalizar")
	public void finalizar() {
		result.redirectTo(this).listaTurmas();
	}
	
	@Get("/turma/removerAlunosDaTurma")
	public void removerAlunosDaTurma(Turma turma) {
		Turma turmaFindable = turmaDAO.buscaTurma(turma);
		
		result.include("turma", turmaFindable);
		result.include("alunos", alunoDAO.listaAlunosDaTurma(turmaFindable));
	}
	
	@Interceptar
	@Post("/turma/removerAluno")
	public void removerAluno(Aluno aluno, Turma turma) {
		Turma turmaFindable = turmaDAO.buscaTurma(turma);
		Aluno alunoFindable = alunoDAO.buscaPorCPF(aluno);
		
		alunoFindable.setTurma(null);
		alunoDAO.alterar(alunoFindable);
		
		turmaFindable.getListaAlunos().remove(alunoFindable);
		turmaDAO.atualizarTurma(turmaFindable);
		
		result.redirectTo(this).listaTurmas();
	}
	
	private void validarDadosTurma(Turma turma) {
		validator.check(turma.getNomeTurma() != null && turma.getNomeTurma().length() > 5, new I18nMessage("turma", "modelo.turma.nome.invalido"));
		validator.check(turma.getCurso() != null && turma.getCurso().getIdCurso() != 0, new I18nMessage("turma", "modelo.turma.curso.naoselecionado"));
		validator.onErrorUsePageOf(this).formularioTurma();
	}

}
