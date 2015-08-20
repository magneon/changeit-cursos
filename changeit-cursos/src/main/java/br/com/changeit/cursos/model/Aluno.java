package br.com.changeit.cursos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Class Aluno.
 */
@Entity
@Table(name = "aluno")
@NamedQueries({
	@NamedQuery(name = Aluno.QUERY_LISTA_ALUNOS, query = "SELECT a FROM Aluno a"),
	@NamedQuery(name = Aluno.QUERY_LISTA_ALUNOS_SEM_TURMA, query = "SELECT a FROM Aluno a WHERE a.turma.idTurma is null"),
	@NamedQuery(name = Aluno.QUERY_LISTA_ALUNOS_DA_TURMA, query = "SELECT a FROM Aluno a WHERE a.turma.idTurma = :idTurma"),
	@NamedQuery(name = Aluno.QUERY_BUSCA_POR_CPF_E_SENHA, query = "SELECT a FROM Aluno a WHERE a.cpf = :cpf AND a.senha = :senha")
})
public class Aluno implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2485335935428213249L;
	
	/** The Constant QUERY_LISTA_ALUNOS. */
	public static final String QUERY_LISTA_ALUNOS = "Aluno.listaAlunos";
	
	/** The Constant QUERY_LISTA_ALUNOS_SEM_TURMA. */
	public static final String QUERY_LISTA_ALUNOS_SEM_TURMA = "Aluno.listaAlunosSemTurma";
	
	/** The Constant QUERY_LISTA_ALUNOS_DA_TURMA. */
	public static final String QUERY_LISTA_ALUNOS_DA_TURMA = "Aluno.listaAlunosDaTurma";
	
	/** The Constant QUERY_BUSCA_POR_CPF_E_SENHA. */
	public static final String QUERY_BUSCA_POR_CPF_E_SENHA = "Aluno.buscaPorCPFESenha";

	/** The cpf. */
	@Id
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	/** The senha. */
	@Column(name = "senha", nullable = false)
	private String senha;
	
	/** The nome aluno. */
	@Column(name = "nome_aluno", nullable = false)
	private String nomeAluno;
	
	/** The idade aluno. */
	@Column(name = "idade_aluno", nullable = false)
	private Integer idadeAluno;
	
	/** The turma. */
	@JoinColumn(name = "id_turma")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Turma.class)
	private Turma turma;

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the senha.
	 *
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Sets the senha.
	 *
	 * @param senha the new senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Gets the nome aluno.
	 *
	 * @return the nome aluno
	 */
	public String getNomeAluno() {
		return nomeAluno;
	}

	/**
	 * Sets the nome aluno.
	 *
	 * @param nomeAluno the new nome aluno
	 */
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	/**
	 * Gets the idade aluno.
	 *
	 * @return the idade aluno
	 */
	public Integer getIdadeAluno() {
		return idadeAluno;
	}

	/**
	 * Sets the idade aluno.
	 *
	 * @param idadeAluno the new idade aluno
	 */
	public void setIdadeAluno(Integer idadeAluno) {
		this.idadeAluno = idadeAluno;
	}

	/**
	 * Gets the turma.
	 *
	 * @return the turma
	 */
	public Turma getTurma() {
		return turma;
	}

	/**
	 * Sets the turma.
	 *
	 * @param turma the new turma
	 */
	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
