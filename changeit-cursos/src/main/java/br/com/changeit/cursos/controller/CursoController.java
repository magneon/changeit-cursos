package br.com.changeit.cursos.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.changeit.cursos.annotations.Interceptar;
import br.com.changeit.cursos.dao.CursoDAO;
import br.com.changeit.cursos.model.Curso;

/**
 * The Class CursoController.
 */
@Controller
public class CursoController {
	
	/** The dao. */
	private final CursoDAO dao;
	
	/** The result. */
	private final Result result;

	/** The validator. */
	private final Validator validator;
	
	/**
	 * Instantiates a new curso controller.
	 */
	@Deprecated
	public CursoController() {
		this(null, null, null);
	}

	/**
	 * Instantiates a new curso controller.
	 *
	 * @param dao the dao
	 * @param result the result
	 */
	@Inject
	public CursoController(CursoDAO dao, Result result, Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}
	
	/**
	 * Formulario curso.
	 */
	@Get("/curso/novo")
	public void formularioCurso(Curso curso) {
		if (curso.getIdCurso() != null) {
			result.include("curso", dao.busca(curso.getIdCurso()));
		}
	}
	
	/**
	 * Lista cursos.
	 */
	@Get("/curso/lista")
	public void listaCursos() {
		result.include("cursos", dao.listaCursos());
	}
	
	/**
	 * Adicionar.
	 *
	 * @param curso the curso
	 */
	@Interceptar
	@Post("/curso/adicionar")
	public void adicionar(Curso curso) {
		if (curso.getIdCurso() != null) {
			alterar(curso);
			return;
		}
		
		validarDadosCurso(curso);
		
		dao.adicionar(curso);
		result.include("mensagem", "Curso adicionado com sucesso!");
		result.redirectTo(this).listaCursos();
	}

	/**
	 * Remover.
	 *
	 * @param curso the curso
	 */
	@Get
	@Interceptar
	public void remover(Curso curso) {
		dao.remover(curso);
		result.include("mensagem", "Curso removido com sucesso!");
		result.redirectTo(this).listaCursos();
	}
	
	/**
	 * Alterar.
	 *
	 * @param curso the curso
	 */
	@Post
	@Interceptar
	public void alterar(Curso curso) {
		
		validarDadosCurso(curso);
		
		dao.alterar(curso);
		result.include("mensagem", "Curso alterado com sucesso!");
		result.redirectTo(this).listaCursos();
	}
	
	@Get("/curso/listaJSON")
	public void listaCursosJSON() {
		result.use(Results.json()).from(dao.listaCursos()).serialize();
	}
	
	/**
	 * Validar dados curso.
	 *
	 * @param curso the curso
	 */
	private void validarDadosCurso(Curso curso) {
		
		validator.check(curso.getNomeCurso() != null && curso.getNomeCurso().length() > 3, new I18nMessage("curso", "modelo.curso.nome.invalido"));
		validator.check(curso.getDescricaoCurso() != null && curso.getDescricaoCurso().length() > 20, new I18nMessage("curso", "modelo.curso.descricao.invalido"));
		validator.check(curso.getValorCurso() != null && curso.getDescricaoCurso().length() > 0, new I18nMessage("curso", "modelo.curso.valor.invalido"));
		validator.onErrorUsePageOf(this).formularioCurso(curso);
		
	}

}
