package br.com.changeit.cursos.interceptors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.changeit.cursos.annotations.Interceptar;

/**
 * The Class TransacaoInterceptor.
 */
@Intercepts
public class TransacaoInterceptor {
	
	/** The controller method. */
	private final ControllerMethod controllerMethod;
	
	/** The entity manager. */
	private final EntityManager entityManager;
	
	/**
	 * Instantiates a new transacao interceptor.
	 */
	@Deprecated
	public TransacaoInterceptor() {
		this(null, null);
	}
	
	/**
	 * Instantiates a new transacao interceptor.
	 *
	 * @param entityManager the entity manager
	 */
	@Inject
	public TransacaoInterceptor(EntityManager entityManager, ControllerMethod controllerMethod) {
		this.entityManager = entityManager;
		this.controllerMethod = controllerMethod;
	}
	
	/**
	 * Interceptar.
	 *
	 * @return true, if successful
	 */
	@Accepts
	public boolean interceptar() {
		return controllerMethod.containsAnnotation(Interceptar.class);
	}
	
	/**
	 * Iniciar transacao.
	 */
	@BeforeCall
	public void iniciarTransacao() {
		entityManager.getTransaction().begin();
	}
	
	/**
	 * Finalizar transacao.
	 */
	@AfterCall
	public void finalizarTransacao() {
		entityManager.getTransaction().commit();
	}

}
