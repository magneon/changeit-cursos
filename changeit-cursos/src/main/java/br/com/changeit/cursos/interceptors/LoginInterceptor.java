package br.com.changeit.cursos.interceptors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.changeit.cursos.annotations.Logar;
import br.com.changeit.cursos.controller.LoginController;
import br.com.changeit.cursos.model.AlunoLogado;

@Intercepts
public class LoginInterceptor {
	
	private final Validator validator;
	private final AlunoLogado alunoLogado;
	private final ControllerMethod controllerMethod;
	
	@Deprecated
	public LoginInterceptor() {
		this(null, null, null);
	}

	@Inject
	public LoginInterceptor(AlunoLogado alunoLogado, Validator validator, ControllerMethod controllerMethod) {
		this.alunoLogado = alunoLogado;
		this.validator = validator;
		this.controllerMethod = controllerMethod;
	}
	
	/**
	 * Interceptar.
	 *
	 * @return true, if successful
	 */
	@Accepts
	public boolean interceptar() {
		return !this.controllerMethod.containsAnnotation(Logar.class);
	}
	
	@AroundCall
	@RequestScoped
	public void verificarSeUsuarioEstaLogado(SimpleInterceptorStack stack) {
		
		validator.check(alunoLogado.getAluno() != null, new I18nMessage("login", "modelo.aluno.login.necessario"));
		validator.onErrorUsePageOf(LoginController.class).inicio();
		
		if (alunoLogado.getAluno() != null) {
			stack.next();
		}
		
	}

}
