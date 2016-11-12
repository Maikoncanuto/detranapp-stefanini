package com.stefanini.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Proprietario;
import com.stefanini.model.Telefone;
import com.stefanini.service.ProprietarioService;
import com.stefanini.util.FacesUtil;

@Named("proprietarioMB")
@SessionScoped
public class ProprietarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProprietarioService proprietarioService;
	
	@Inject
	private Proprietario proprietario;
	
	@Inject
	private Telefone telefone;

	public void salvar() {
		try {
			proprietarioService.incluir(proprietario);
			FacesUtil.exibeSucesso("REGISTRO INSERIDO COM SUCESSO");
		} catch (RuntimeException e) {
			FacesUtil.exibeErro("PROBLEMA AO INSERIR REGISTRO");
		}
		
		this.proprietario = new Proprietario();
		this.telefone = new Telefone();
	}

	public void salvarTelefone() {
		if (this.proprietario != null) {
			this.proprietario.adicionarTelefone(telefone);
			FacesUtil.exibeSucesso("REGISTRO INSERIDO COM SUCESSO");
		}else{
			FacesUtil.exibeErro("PROBLEMA AO INSERIR REGISTRO");
			this.telefone = new Telefone();
		}

	}
	
	public void excluir(Proprietario proprietario){
		try{
			proprietarioService.remover(proprietario);
			FacesUtil.exibeSucesso("REGISTRO REMOVIDO COM SUCESSO");
		}catch(RuntimeException e){
			FacesUtil.exibeErro("PROBLEMA AO REMOVER REGISTRO");
		}
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public List<Proprietario> proprietarios(){
		return proprietarioService.lista();
	}

}