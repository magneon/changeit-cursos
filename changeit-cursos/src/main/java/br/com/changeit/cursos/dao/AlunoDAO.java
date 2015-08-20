package br.com.changeit.cursos.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.changeit.cursos.model.Aluno;
import br.com.changeit.cursos.model.Turma;

/**
 * The Class AlunoDAO.
 */
public class AlunoDAO {
	
	/** The entity manager. */
	private final EntityManager entityManager;

	/**
	 * Instantiates a new aluno dao.
	 */
	@Deprecated
	public AlunoDAO() {
		this(null);
	}
	
	/**
	 * Instantiates a new aluno dao.
	 *
	 * @param entityManager the entity manager
	 */
	@Inject
	public AlunoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Lista alunos.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listaAlunos() {
		Query query = this.entityManager.createNamedQuery(Aluno.QUERY_LISTA_ALUNOS);
		return query.getResultList();
	}
	
	/**
	 * Adicionar ou atualizar.
	 *
	 * @param aluno the aluno
	 */
	public void adicionar(Aluno aluno) throws EntityExistsException {
		Aluno finded = this.entityManager.find(aluno.getClass(), aluno.getCpf());
		if (finded != null) {
			throw new EntityExistsException();
		} else {
			this.entityManager.persist(aluno);
		}
	}
	
	/**
	 * Remover.
	 *
	 * @param aluno the aluno
	 */
	public void remover(Aluno aluno) {
		this.entityManager.remove(buscaPorCPF(aluno));
	}
	
	/**
	 * Busca por cpf.
	 *
	 * @param aluno the aluno
	 * @return the aluno
	 */
	public Aluno buscaPorCPF(Aluno aluno) {
		return this.entityManager.find(aluno.getClass(), aluno.getCpf());
	}
	
	/**
	 * Alterar.
	 *
	 * @param aluno the aluno
	 * @return the aluno
	 */
	public Aluno alterar(Aluno aluno) {
		return this.entityManager.merge(aluno);
	}

	/**
	 * Lista alunos sem turma.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listaAlunosSemTurma() {
		Query query = this.entityManager.createNamedQuery(Aluno.QUERY_LISTA_ALUNOS_SEM_TURMA);
		return query.getResultList();
	}
	
	/**
	 * Lista alunos da turma.
	 *
	 * @param turma the turma
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Aluno> listaAlunosDaTurma(Turma turma) {
		Query query = this.entityManager.createNamedQuery(Aluno.QUERY_LISTA_ALUNOS_DA_TURMA);
		query.setParameter("idTurma", turma.getIdTurma());
		
		return query.getResultList();
	}

	/**
	 * Busca por cpfe senha.
	 *
	 * @param aluno the aluno
	 * @return the aluno
	 */
	public Aluno buscaPorCPFESenha(Aluno aluno) {
		Query query = this.entityManager.createNamedQuery(Aluno.QUERY_BUSCA_POR_CPF_E_SENHA);
		query.setParameter("cpf", aluno.getCpf());
		query.setParameter("senha", aluno.getSenha());
		
		return (Aluno) query.getSingleResult();
	}

}
