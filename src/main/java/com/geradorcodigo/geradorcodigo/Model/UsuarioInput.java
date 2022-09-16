package com.geradorcodigo.geradorcodigo.Model;

public class UsuarioInput {

        private int id;
    private String login;
    private String senha;
    private int pessoa;
    private String foto;
    
public String getFoto() {
        return foto;
}
public void setFoto(String foto) {
        this.foto = foto;
}
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
public int getPessoa() {
        return pessoa;
}
public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
}
    
    
}