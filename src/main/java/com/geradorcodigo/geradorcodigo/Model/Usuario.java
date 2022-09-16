package com.geradorcodigo.geradorcodigo.Model;

public class Usuario {

    private int id;
    private String login;
    private String senha;
    private Pessoa pessoa;
    public String getFoto() {
        return foto;
}
public void setFoto(String foto) {
        this.foto = foto;
}
private String foto;
    
public int getId() {
        return id;
}
public void setId(int id) {
        this.id = id;
}
public String getLogin() {
        return login;
}
public void setLogin(String login) {
        this.login = login;
}
public String getSenha() {
        return senha;
}
public void setSenha(String senha) {
        this.senha = senha;
}
public Pessoa getPessoa() {
        return pessoa;
}
public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
}
    
    
}
