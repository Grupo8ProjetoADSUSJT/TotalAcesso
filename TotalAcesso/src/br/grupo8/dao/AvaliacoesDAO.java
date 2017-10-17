package br.grupo8.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import br.grupo8.entity.Avaliacoes;
import br.grupo8.entity.Categoria;
@Repository
public class AvaliacoesDAO {
Connection conn;
	public int inserirAvaliacoes(Avaliacoes avaliacoes) throws IOException {
		int id = -1;
		String comando = "insert into Avaliacao (comentario, avaliacao1,avaliacao2,avaliacao3"
				+ ",avaliacao4, id_categoria, dataAvaliacao) "
				+ "values (?,?,?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(comando);) {
			pst.setString(1, avaliacoes.getComentario());
			pst.setDouble(2, avaliacoes.getAvaliacao1());
			pst.setDouble(3, avaliacoes.getAvaliacao2());
			pst.setDouble(4, avaliacoes.getAvaliacao3());
			pst.setDouble(5, avaliacoes.getAvaliacao4());
			pst.setDate(6, new java.sql.Date(avaliacoes.getDataAvaliacao()
					.getTime()));
			pst.setInt(7, avaliacoes.getCategoria().getId());
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
	
	public ArrayList<Avaliacoes> listarAvaliacoes(Categoria categoria) throws IOException{
		ArrayList<Avaliacoes> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.dt_abertura, f.nm_fila, "+
				"c.dt_fechamento, c.status "+ 
				"from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=?";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, categoria.getId());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					Avaliacoes avaliacoes = new Avaliacoes();
					avaliacoes.setNumero(rs.getInt("id_avaliacao"));
					avaliacoes.setComentario(rs.getString("comentario"));
					avaliacoes.setAvaliacao1(rs.getDouble("avaliacao1"));
					avaliacoes.setAvaliacao2(rs.getDouble("avaliacao2"));
					avaliacoes.setAvaliacao3(rs.getDouble("avaliacao3"));
					avaliacoes.setAvaliacao4(rs.getDouble("avaliacao4"));
					avaliacoes.setDataAvaliacao(rs.getDate("dt_avaliacao"));
					categoria.setNome(rs.getString("nome_categoria"));
					avaliacoes.setCategoria(categoria);
					lista.add(avaliacoes);
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
