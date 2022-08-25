package com.geradorcodigo.geradorcodigo.Model;

public class Pessoa {

    private int id;
    private String nome;
    private String email;
    private String cref;
    private String cpf;
    private Aluno aluno;
    private Personal personal;
    
public int getId() {
        return id;
}
public void setId(int id) {
        this.id = id;
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
public String getCref() {
        return cref;
}
public void setCref(String cref) {
        this.cref = cref;
}
public String getCpf() {
        return cpf;
}
public void setCpf(String cpf) {
        this.cpf = cpf;
}
public Aluno getAluno() {
        return aluno;
}
public void setAluno(Aluno aluno) {
        this.aluno = aluno;
}
public Personal getPersonal() {
        return personal;
}
public void setPersonal(Personal personal) {
        this.personal = personal;
}

    
}
