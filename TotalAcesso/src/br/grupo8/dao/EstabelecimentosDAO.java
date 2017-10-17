package br.grupo8.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.grupo8.entity.Estabelecimentos;

public class EstabelecimentosDAO {
	Connection conn;
	public int inserirEstabelecimento(Estabelecimentos estabelecimentos) throws IOException {
		int id = -1;
		String comando = "insert into chamado (descricao, status, dt_abertura, id_fila) "
				+ "values (?,?,?,?)";
		try (PreparedStatement pst = conn.prepareStatement(comando);) {
			pst.setString(1, estabelecimentos.getDescricao());
			pst.setString(2, estabelecimentos.getStatus());
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
}
