package com.geradorcodigo.geradorcodigo.Model;

public class VariacaoExercicioInput {

    private Integer id;
    private String descricao;
    private Integer musculo;
    private Integer professor;
    private String urlImagem;
    private String urlVideo;
    private String instrucao;
    private Integer isVariacao;
    private Integer exercicio;
    
    public String getUrlVideo() {
        return urlVideo;
}
public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
}
public String getInstrucao() {
        return instrucao;
}
public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
}
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
public Integer getMusculo() {
        return musculo;
}
public void setMusculo(Integer musculo) {
        this.musculo = musculo;
}
public Integer getProfessor() {
        return professor;
}
public void setProfessor(Integer professor) {
        this.professor = professor;
}
public String getUrlImagem() {
        return urlImagem;
}
public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
}
public Integer getIsVariacao() {
        return isVariacao;
}
public void setIsVariacao(Integer isVariacao) {
        this.isVariacao = isVariacao;
}
public Integer getExercicio() {
        return exercicio;
}
public void setExercicio(Integer exercicio) {
        this.exercicio = exercicio;
}

    
}
