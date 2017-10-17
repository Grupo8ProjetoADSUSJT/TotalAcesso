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
import br.grupo8.entity.Estabelecimentos;
@Repository
public class EstabelecimentosDAO {
	Connection conn;
	
	@Autowired
	public EstabelecimentosDAO(DataSource ds) {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int inserirEstabelecimento(Estabelecimentos estabelecimentos) throws IOException {
		int id = -1;
		String comando = "insert into chamado (nome, endereco, telefone,avalicao_final, id_categoria) "
				+ "values (?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(comando);) {
			pst.setString(1, estabelecimentos.getNome());
			pst.setString(2, estabelecimentos.getEnd());
			pst.setDate(3, new java.sql.Date(estabelecimentos.getDataAbertura()
					.getTime()));
			pst.setInt(4, estabelecimentos.getCategoria().getId());
			pst.execute();
			// pegar o ultimo id inserido nesta sessao
			try (PreparedStatement pst1 = conn
					.prepareStatement("select LAST_INSERT_ID()");
					ResultSet rs = pst1.executeQuery();) {
				rs.next();
				id = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}
	public ArrayList<Estabelecimentos> listarEstabelecimentos(Categoria categoria) throws IOException{
		ArrayList<Estabelecimentos> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.dt_abertura, f.nm_fila, "+
				"c.dt_fechamento, c.status "+ 
				"from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=?";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, categoria.getId());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					Estabelecimentos estabelecimentos= new Estabelecimentos();
					estabelecimentos.setId(rs.getInt("id_estabelecimento"));
					estabelecimentos.setNome(rs.getString("nome"));
					estabelecimentos.setTel(rs.getInt("telefone"));
					estabelecimentos.setEnd(rs.getString("endereco"));
					estabelecimentos.setAvaliacaofinal(rs.getDouble("avalicao_final"));
					categoria.setNome(rs.getString("nome_categoria"));
					estabelecimentos.setCategoria(categoria);
					lista.add(estabelecimentos);
				}
			} catch(SQLException e){
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}

}
