package br.com.caelum.livraria.transaçao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transactional
@Interceptor
public class TransactionManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object executTransaction(InvocationContext context) throws Exception {
		manager.getTransaction().begin();
		
		Object result = context.proceed();
		
		manager.getTransaction().commit();
		
		return result;
	}
}
