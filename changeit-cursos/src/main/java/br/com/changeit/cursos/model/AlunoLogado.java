package br.com.changeit.cursos.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * The Class AlunoLogado.
 */
@Named
@SessionScoped
public class AlunoLogado implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7097676025500593383L;
	
	/** The aluno. */
	private Aluno aluno;

	/**
	 * Gets the aluno.
	 *
	 * @return the aluno
	 */
	public Aluno getAluno() {
		return aluno;
	}

	/**
	 * Sets the aluno.
	 *
	 * @param aluno the new aluno
	 */
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
