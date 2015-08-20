package br.com.changeit.cursos.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Curso.
 */
@Entity
@Table(name = "curso")
@NamedQueries({
	@NamedQuery(name = Curso.QUERY_LISTA_CURSOS, query = "SELECT c FROM Curso c")
})
public class Curso {
	
	/** The Constant QUERY_LISTA_CURSOS. */
	public static final String QUERY_LISTA_CURSOS = "Curso.listaCursos";

	/** The id curso. */
	@Id
	@Column(name = "id_curso")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCurso;
	
	/** The nome curso. */
	@Column(name = "nome_curso")
	private String nomeCurso;
	
	/** The descricao curso. */
	@Column(name = "descricao_curso")
	private String descricaoCurso;
	
	/** The valor curso. */
	@Column(name = "valor_curso")
	private Double valorCurso;
	
	/** The lista turmas. */
	@OneToMany(targetEntity = Turma.class, fetch = FetchType.LAZY, mappedBy = "curso")
	private List<Turma> listaTurmas;

	/**
	 * Instantiates a new curso.
	 */
	@Deprecated
	public Curso() {
	}

	/**
	 * Instantiates a new curso.
	 *
	 * @param idCurso the id curso
	 * @param nomeCurso the nome curso
	 * @param descricaoCurso the descricao curso
	 * @param valorCurso the valor curso
	 */
	public Curso(Long idCurso, String nomeCurso, String descricaoCurso, Double valorCurso) {
		super();
		this.idCurso = idCurso;
		this.nomeCurso = nomeCurso;
		this.descricaoCurso = descricaoCurso;
		this.valorCurso = valorCurso;
	}

	/**
	 * Gets the id curso.
	 *
	 * @return the id curso
	 */
	public Long getIdCurso() {
		return idCurso;
	}

	/**
	 * Sets the id curso.
	 *
	 * @param idCurso the new id curso
	 */
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	/**
	 * Gets the nome curso.
	 *
	 * @return the nome curso
	 */
	public String getNomeCurso() {
		return nomeCurso;
	}

	/**
	 * Sets the nome curso.
	 *
	 * @param nomeCurso the new nome curso
	 */
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	/**
	 * Gets the descricao curso.
	 *
	 * @return the descricao curso
	 */
	public String getDescricaoCurso() {
		return descricaoCurso;
	}

	/**
	 * Sets the descricao curso.
	 *
	 * @param descricaoCurso the new descricao curso
	 */
	public void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}

	/**
	 * Gets the valor curso.
	 *
	 * @return the valor curso
	 */
	public Double getValorCurso() {
		return valorCurso;
	}

	/**
	 * Sets the valor curso.
	 *
	 * @param valorCurso the new valor curso
	 */
	public void setValorCurso(Double valorCurso) {
		this.valorCurso = valorCurso;
	}

	/**
	 * Gets the lista turmas.
	 *
	 * @return the lista turmas
	 */
	public List<Turma> getListaTurmas() {
		return listaTurmas;
	}

	/**
	 * Sets the lista turmas.
	 *
	 * @param listaTurmas the new lista turmas
	 */
	public void setListaTurmas(List<Turma> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}

}
