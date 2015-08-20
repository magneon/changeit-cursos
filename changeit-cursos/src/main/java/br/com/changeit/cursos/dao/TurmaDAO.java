package br.com.changeit.cursos.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.changeit.cursos.model.Turma;

/**
 * The Class TurmaDAO.
 */
public class TurmaDAO {
	
	/** The entity manager. */
	private final EntityManager entityManager;

	/**
	 * Instantiates a new turma dao.
	 */
	public TurmaDAO() {
		this(null);
	}
	
	/**
	 * Instantiates a new turma dao.
	 *
	 * @param entityManager the entity manager
	 */
	@Inject
	public TurmaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Lista turmas.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Turma> listaTurmas() {
		Query query = this.entityManager.createNamedQuery(Turma.QUERY_LISTA_TURMAS);
		return query.getResultList();
	}
	
	/**
	 * Adicionar turma.
	 *
	 * @param turma the turma
	 */
	public void adicionarTurma(Turma turma) {
		this.entityManager.persist(turma);
	}
	
	/**
	 * Remover turma.
	 *
	 * @param turma the turma
	 */
	public void removerTurma(Turma turma) {
		this.entityManager.remove(buscaTurma(turma));
	}

	/**
	 * Busca turma.
	 *
	 * @param turma the turma
	 * @return the turma
	 */
	public Turma buscaTurma(Turma turma) {
		return this.entityManager.find(turma.getClass(), turma.getIdTurma());
	}

	/**
	 * Atualizar turma.
	 *
	 * @param turma the turma
	 * @return the turma
	 */
	public Turma atualizarTurma(Turma turma) {
		return this.entityManager.merge(turma);		
	}

}
