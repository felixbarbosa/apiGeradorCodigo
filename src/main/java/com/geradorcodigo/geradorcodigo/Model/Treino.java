package com.geradorcodigo.geradorcodigo.Model;

public class Treino {

    private Integer id;
    private String nome;
    private Exercicio exercicio;
    private Aluno aluno;
    private String repeticoes;
    private String velocidade;
    private String descanso;
    private Musculo musculoAlvo;
    private DiaSemana diaSemana;
    private String series;
    
public Integer getId() {
            return id;
    }
    public void setId(Integer id) {
            this.id = id;
    }
public String getNome() {
        return nome;
}
public void setNome(String nome) {
        this.nome = nome;
}
public Exercicio getExercicio() {
        return exercicio;
}
public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
}
public Aluno getAluno() {
        return aluno;
}
public void setAluno(Aluno aluno) {
        this.aluno = aluno;
}
public String getRepeticoes() {
        return repeticoes;
}
public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
}
public String getVelocidade() {
        return velocidade;
}
public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
}
public String getDescanso() {
        return descanso;
}
public void setDescanso(String descanso) {
        this.descanso = descanso;
}
public Musculo getMusculoAlvo() {
        return musculoAlvo;
}
public void setMusculoAlvo(Musculo musculoAlvo) {
        this.musculoAlvo = musculoAlvo;
}
public DiaSemana getDiaSemana() {
        return diaSemana;
}
public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
}
public String getSeries() {
        return series;
}
public void setSeries(String series) {
        this.series = series;
}

    
}
