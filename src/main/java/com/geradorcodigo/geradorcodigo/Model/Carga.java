package com.geradorcodigo.geradorcodigo.Model;

public class Carga {

    private int id;
    private String carga;
    private Aluno aluno;
    private String data;
    private Exercicio exercicio;
    
public int getId() {
        return id;
}
public void setId(int id) {
        this.id = id;
}
public String getCarga() {
        return carga;
}
public void setCarga(String carga) {
        this.carga = carga;
}
public Aluno getAluno() {
        return aluno;
}
public void setAluno(Aluno aluno) {
        this.aluno = aluno;
}
public String getData() {
        return data;
}
public void setData(String data) {
        this.data = data;
}
public Exercicio getExercicio() {
        return exercicio;
}
public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
}
    

}
