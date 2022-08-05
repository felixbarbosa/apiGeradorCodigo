package com.geradorcodigo.geradorcodigo.Model;

public class ExercicioInput {

        private Integer id;
        private String descricao;
        private Integer musculo;
        private Integer professor;
        
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
        
        
}