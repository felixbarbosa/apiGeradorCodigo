package com.geradorcodigo.geradorcodigo.Graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;
import com.geradorcodigo.geradorcodigo.Model.Usuario;
import com.geradorcodigo.geradorcodigo.Model.UsuarioInput;
import com.geradorcodigo.geradorcodigo.Repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> obterUsuario(String login, String senha){

        List<Usuario> lista = usuarioRepo.obterUsuario(login, senha);
        return lista;    
    }

    public Usuario salvarUsuario(UsuarioInput usuarioInput){
 
        Usuario usuario = new Usuario();
        Pessoa pessoa = new Pessoa();

        usuario.setId(usuarioInput.getId());
        usuario.setLogin(usuarioInput.getLogin());
        usuario.setSenha(usuarioInput.getSenha());
        usuario.setFoto(usuarioInput.getFoto());

        pessoa.setId(usuarioInput.getPessoa());
        usuario.setPessoa(pessoa);

        if(usuario.getId() == 0){
            usuario = usuarioRepo.salvarUsuario(usuario);
        }
        return usuario;
    }
      
}
