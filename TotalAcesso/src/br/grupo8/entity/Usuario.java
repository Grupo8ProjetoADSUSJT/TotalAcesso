package br.grupo8.entity;

import java.io.Serializable;

public class Usuario implements Serializable{
	private int id;
	private String username;
	private String password;
	private String nome;
	private String email;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString(){
		return "Usuario [id_usuario=" + id + ", usuario=" + username + 
				",senha=" + password + ", nome=" + nome + ", email="+ email +"]";
	}
	
}
