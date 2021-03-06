package br.com.mk.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.mk.model.TipoInfracao;

public class TipoRepository {

	@Inject
	private EntityManager manager;

	public void incluir(TipoInfracao tipo) {
		this.manager.persist(tipo);
	}

	public void altera(TipoInfracao tipo) {
		this.manager.merge(tipo);
	}

	public TipoInfracao busca(Integer id) {
		return this.manager.find(TipoInfracao.class, id);
	}

	public List<TipoInfracao> lista() {
		return this.manager.createQuery("select t from TipoInfracao t", TipoInfracao.class).getResultList();
	}
	
	public void remover(TipoInfracao tipoinfracao){
		this.manager.remove(tipoinfracao);
	}
	
	public void remover(Integer id){
		this.manager.remove(this.busca(id));
	}
}
