package br.grupo8.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.grupo8.dao.EstabelecimentosDAO;
import br.grupo8.entity.Categoria;
import br.grupo8.entity.Estabelecimentos;

@Service
public class EstabelecimentosService{
	
	EstabelecimentosDAO dao;
	
	@Autowired
	public EstabelecimentosService(EstabelecimentosDAO dao){
		this.dao=dao;
	}
	
	public int novoEstabelecimento(Estabelecimentos estabelecimentos) throws IOException{
		estabelecimentos.setDataAbertura(new Date());
		estabelecimentos.setDataFechamento(null);
		estabelecimentos.setStatus(Estabelecimentos.ABERTO);
		
		return dao.inserirEstabelecimento(estabelecimentos);
	}
	
	public ArrayList<Estabelecimentos> listarEstabelecimentos(Categoria categoria) throws IOException{
		return dao.listarEstabelecimentos(categoria);
	}

}
