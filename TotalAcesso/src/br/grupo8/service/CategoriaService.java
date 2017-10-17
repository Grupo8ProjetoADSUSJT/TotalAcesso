package br.grupo8.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.grupo8.dao.CategoriaDAO;
import br.grupo8.entity.Categoria;
@Service
public class CategoriaService {
	public CategoriaDAO dao;
	
	@Autowired
	public CategoriaService(CategoriaDAO dao){
		this.dao=dao;
	}
	
	public ArrayList<Categoria> listarCategorias() throws IOException{
		return dao.listarCategorias();
	}
	
	public Categoria carregar(int id) throws IOException{
		return dao.carregar(id);
	}

}
