package br.grupo8.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.grupo8.entity.Avaliacoes;
import br.grupo8.entity.Categoria;
import br.grupo8.entity.Estabelecimentos;
import br.usjt.sdesk.model.entity.Chamado;
@Repository
public class AvaliacoesDAO {
Connection conn;
@Autowired
public AvaliacoesDAO(DataSource ds) {
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public int inserirAvaliacao(Avaliacoes avaliacoes) throws IOException {
		int id = -1;
		String comando = "insert into Avaliacao (comentario, avaliacao1,avaliacao2,avaliacao3"
				+ ",avaliacao4, id_categoria, dataAvaliacao, id_estabelecimento) "
				+ "values (?,?,?,?,?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(comando);) {
			pst.setString(1, avaliacoes.getComentario());
			pst.setDouble(2, avaliacoes.getAvaliacao1());
			pst.setDouble(3, avaliacoes.getAvaliacao2());
			pst.setDouble(4, avaliacoes.getAvaliacao3());
			pst.setDouble(5, avaliacoes.getAvaliacao4());
			pst.setDate(6, new java.sql.Date(avaliacoes.getDataAvaliacao()
					.getTime()));
			pst.setInt(7, avaliacoes.getCategoria().getId());
			pst.setInt(8, avaliacoes.getEstabelecimento().getId());
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
	
	public ArrayList<Avaliacoes> listarAvaliacoes(Categoria categoria, Estabelecimentos estabelecimentos) throws IOException{
		ArrayList<Avaliacoes> lista = new ArrayList<>();
		String query = "select a.id_avalicao, a.comentario, a.dt_avalicao, c.nome_categoria, "+
				"a.avaliacao1, a.avaliacao2,a.avaliacao3,a.avaliacao4, nome_estabelecimento "+ 
				"from avaliacao a, categoria c,Estabelecimento e where c.id_categoria = a.id_categoria "
				+ "and c.id_categoria=?"
				+ "and e.id_estabelecimento = a.id_estabelecimento"
				+ "and e.id_estabelecimento = ?";
		
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
					estabelecimentos.setNome(rs.getString("nome_estabelecimento"));
					avaliacoes.setEstabelecimento(estabelecimentos);
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
	
	public void AlterarAvaliacao(ArrayList<Integer> lista) throws IOException{
		String comando = "update chamado set estabelecimento=?, comentario=?, avaliacao1=?"
				+ ", avaliacao2=?, avaliacao3=?, avaliacao4=? where id_avaliacao=?";
		
		try(PreparedStatement pst = conn.prepareStatement(comando);){
			
			for(int id:lista){
				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setString(2, Avaliacoes.FECHADO);
				pst.setInt(3, id);
				pst.execute();
			}
			
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		
	}
	
}
