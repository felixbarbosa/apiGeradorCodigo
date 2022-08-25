package com.geradorcodigo.geradorcodigo.Repository;
import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Usuario;

public interface UsuarioRepository {

    List<Usuario> obterUsuario(String login, String senha);

    Usuario salvarUsuario(Usuario usuario);

    //Aluno atualizarAluno(Aluno aluno);
}
