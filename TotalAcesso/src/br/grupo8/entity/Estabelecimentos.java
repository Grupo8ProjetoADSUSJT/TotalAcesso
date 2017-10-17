package br.grupo8.entity;

public class Estabelecimentos {
	
	private String nome;
	private int id;
	private String end;
	private int tel;
	private double avaliacaofinal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	
	public double getAvaliacaofinal() {
		return avaliacaofinal;
	}
	public void setAvaliacaofinal(double avaliacaofinal) {
		this.avaliacaofinal = avaliacaofinal;
	}
	@Override
	public String toString() {
		return "Estabelecimento [id_estabelecimento=" + id + ", nome_fantasia=" + nome + 
				",endereco=" + end+ ", telefone=" + tel+ "]";
	}
	

}
