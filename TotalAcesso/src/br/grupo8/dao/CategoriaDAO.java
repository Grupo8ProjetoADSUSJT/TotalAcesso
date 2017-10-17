package br.grupo8.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.grupo8.entity.Categoria;
@Repository
public class CategoriaDAO {
	Connection conn;
	@Autowired
	public CategoriaDAO(DataSource ds) {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Categoria> listarCategorias() throws IOException {
		String query = "select id_categoria, nome_categoria from Categoria";
		ArrayList<Categoria> categorias = new ArrayList<>();
		
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()){
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id_categoria"));
				categoria.setNome(rs.getString("nome_categoria"));
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return categorias;
	}

	public Categoria carregar(int id) throws IOException {
		Categoria categoria = new Categoria();
		categoria.setId(id);
		String query = "select nome_categoria from Categoria where id_categoria=?";

		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					categoria.setNome(rs.getString("nome_categoria"));
				} else {
					categoria.setNome(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return categoria;
	}
}
