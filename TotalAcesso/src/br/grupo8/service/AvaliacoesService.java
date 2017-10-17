package br.grupo8.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.grupo8.dao.AvaliacoesDAO;
import br.grupo8.entity.Avaliacoes;
import br.grupo8.entity.Categoria;
@Service
public class AvaliacoesService {

AvaliacoesDAO dao;
	
	@Autowired
	public AvaliacoesService(AvaliacoesDAO dao){
		this.dao=dao;
	}
	
	public int novoChamado(Avaliacoes avaliacoes) throws IOException{
		avaliacoes.setDataAvaliacao(new Date());
		
		return dao.inserirAvaliacao(avaliacoes);
	}
		
	public ArrayList<Avaliacoes> listarAvaliacoes(Categoria categoria) throws IOException{
		return dao.listarAvaliacoes(categoria);
	}

	
}
