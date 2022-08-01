package com.geradorcodigo.geradorcodigo.Model;

public class Personal {

        private Integer id;
        private String nome;
        private String cpf;
        private String login;
        private String senha;
        private String sexo;
        private String email;
        private Integer idade;
        private String cref;
        
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
            public String getCpf() {
                    return cpf;
            }
            public void setCpf(String cpf) {
                    this.cpf = cpf;
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
            public String getSexo() {
                    return sexo;
            }
            public void setSexo(String sexo) {
                    this.sexo = sexo;
            }
            public String getEmail() {
                    return email;
            }
            public void setEmail(String email) {
                    this.email = email;
            }
            public Integer getIdade() {
                    return idade;
            }
            public void setIdade(Integer idade) {
                    this.idade = idade;
            }
        public String getCref() {
                return cref;
        }
        public void setCref(String cref) {
                this.cref = cref;
        }
}
