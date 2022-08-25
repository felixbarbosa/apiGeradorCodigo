package com.geradorcodigo.geradorcodigo.Repository;
import com.geradorcodigo.geradorcodigo.Model.Usuario;

public interface UsuarioRepository {

    Usuario obterUsuario(String login, String senha);

    Usuario salvarUsuario(Usuario usuario);

    //Aluno atualizarAluno(Aluno aluno);
}
