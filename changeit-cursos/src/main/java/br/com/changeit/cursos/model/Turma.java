package br.com.changeit.cursos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Turma.
 */
@Entity
@Table(name = "turma")
@NamedQueries({
	@NamedQuery(name = Turma.QUERY_LISTA_TURMAS, query = "SELECT t FROM Turma t")
})
public class Turma implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5073544277297285462L;
	
	/** The Constant QUERY_LISTA_TURMAS. */
	public static final String QUERY_LISTA_TURMAS = "Turma.listaTurmas";
	
	/** The id turma. */
	@Id
	@Column(name = "id_turma", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTurma;
	
	/** The nome turma. */
	@Column(name = "nome_turma", nullable = false)
	private String nomeTurma;
	
	/** The curso. */
	@JoinColumn(name = "id_curso")
	@ManyToOne(targetEntity = Curso.class, fetch = FetchType.LAZY)
	private Curso curso;
	
	/** The lista alunos. */
	@OneToMany(targetEntity = Aluno.class, fetch = FetchType.LAZY, mappedBy = "turma")
	private List<Aluno> listaAlunos;

	/**
	 * Gets the id turma.
	 *
	 * @return the id turma
	 */
	public Integer getIdTurma() {
		return idTurma;
	}

	/**
	 * Sets the id turma.
	 *
	 * @param idTurma the new id turma
	 */
	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	/**
	 * Gets the nome turma.
	 *
	 * @return the nome turma
	 */
	public String getNomeTurma() {
		return nomeTurma;
	}

	/**
	 * Sets the nome turma.
	 *
	 * @param nomeTurma the new nome turma
	 */
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	/**
	 * Gets the curso.
	 *
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * Sets the curso.
	 *
	 * @param curso the new curso
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * Gets the lista alunos.
	 *
	 * @return the lista alunos
	 */
	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	/**
	 * Sets the lista alunos.
	 *
	 * @param listaAlunos the new lista alunos
	 */
	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
}
