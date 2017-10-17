package br.grupo8.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Avaliacoes {
	private int numero;
	private Date dataAvaliacao;
	private double avaliacao1;
	private double avaliacao2;
	private double avaliacao3;
	private double avaliacao4;

	@NotNull 
	@Size(max=140,min=10, message="O tamanho da descrição deve estar entre 10 e 140 caracteres")
	private String comentario;
	@NotNull
	private Categoria categoria;
	private Estabelecimentos estabelecimento;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}
	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	public double getAvaliacao1() {
		return avaliacao1;
	}
	public void setAvaliacao1(double avaliacao1) {
		this.avaliacao1 = avaliacao1;
	}
	public double getAvaliacao2() {
		return avaliacao2;
	}
	public void setAvaliacao2(double avaliacao2) {
		this.avaliacao2 = avaliacao2;
	}
	public double getAvaliacao3() {
		return avaliacao3;
	}
	public void setAvaliacao3(double avaliacao3) {
		this.avaliacao3 = avaliacao3;
	}
	public double getAvaliacao4() {
		return avaliacao4;
	}
	public void setAvaliacao4(double avaliacao4) {
		this.avaliacao4 = avaliacao4;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Estabelecimentos getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimentos estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	@Override
	public String toString() {
		return "Avaliacao [numero=" + numero + ", dataAvaliacao=" + dataAvaliacao
				+ ", avaliacao1=" + avaliacao1 + ", avalicao2=" + avaliacao2
				+ ", avaliacao3=" + avaliacao3 + ", avaliacao4=" + avaliacao4 + 
				", comentario="+ comentario + ", categoria=" + categoria +"]";
	}


}
