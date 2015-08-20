package br.com.changeit.cursos.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.changeit.cursos.model.Curso;

/**
 * The Class CursoDAO.
 */
public class CursoDAO {

	/** The entity manager. */
	private final EntityManager entityManager;
	
	/**
	 * Instantiates a new curso dao.
	 */
	@Deprecated
	public CursoDAO() {
		this(null);
	}

	/**
	 * Instantiates a new curso dao.
	 *
	 * @param entityManager the entity manager
	 */
	@Inject
	public CursoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Lista cursos.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Curso> listaCursos() {
		Query query = entityManager.createNamedQuery(Curso.QUERY_LISTA_CURSOS);
		List<Curso> cursos = query.getResultList();
		return cursos;
	}
	
	/**
	 * Adicionar.
	 *
	 * @param curso the curso
	 */
	public void adicionar(Curso curso) {
		entityManager.persist(curso);
	}
	
	/**
	 * Remover.
	 *
	 * @param curso the curso
	 */
	public void remover(Curso curso) {
		entityManager.remove(busca(curso.getIdCurso()));
	}
	
	/**
	 * Busca.
	 *
	 * @param id the id
	 * @return the curso
	 */
	public Curso busca(Long id) {
		return entityManager.find(Curso.class, id);
	}

	/**
	 * Alterar.
	 *
	 * @param curso the curso
	 */
	public void alterar(Curso curso) {
		entityManager.merge(curso);
	}
	
}
