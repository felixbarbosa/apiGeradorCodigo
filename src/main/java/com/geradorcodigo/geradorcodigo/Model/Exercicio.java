package com.geradorcodigo.geradorcodigo.Model;

public class Exercicio {

    private Integer id;
    private String descricao;
    private Musculo musculo;
    private Personal professor;
    
    public Integer getId() {
            return id;
    }
    public void setId(Integer id) {
            this.id = id;
    }
public String getDescricao() {
        return descricao;
}
public void setDescricao(String descricao) {
        this.descricao = descricao;
}
public Musculo getMusculo() {
        return musculo;
}
public void setMusculo(Musculo musculo) {
        this.musculo = musculo;
}
public Personal getProfessor() {
        return professor;
}
public void setProfessor(Personal professor) {
        this.professor = professor;
}
    
    
   

    
}
