package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.PersonalInput;
import com.geradorcodigo.geradorcodigo.Model.Pessoa;
import com.geradorcodigo.geradorcodigo.Model.Usuario;
import com.geradorcodigo.geradorcodigo.Repository.PersonalRepository;
import com.geradorcodigo.geradorcodigo.Repository.PessoaRepository;
import com.geradorcodigo.geradorcodigo.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonalGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PersonalRepository personalRepo;

    @Autowired
    private PessoaRepository pessoaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;


    public List<Personal> obterTodosPersonais(){

        List<Personal> lista = personalRepo.obterTodosPersonais();
        return lista;    
    }

    public Personal obterPersonalPorId(int id){

        Personal personal = personalRepo.obterPersonalPorId(id);
        return personal;    
    }

    public Personal salvarPersonal(PersonalInput personalInput){
 
        Personal personal= new Personal();
        
        personal.setId(personalInput.getId());
        personal.setCpf(personalInput.getCpf());
        personal.setEmail(personalInput.getEmail());
        personal.setIdade(personalInput.getIdade());
        personal.setLogin(personalInput.getLogin());
        personal.setNome(personalInput.getNome());
        personal.setCref(personalInput.getCref());
        personal.setSenha(personalInput.getSenha());
        personal.setSexo(personalInput.getSexo());
        personal.setFoto(personalInput.getFoto());

        if(personal.getId() == 0){
            personal = personalRepo.salvarPersonal(personal);

            Pessoa pessoa = new Pessoa();
            Usuario usuario = new Usuario();

            pessoa.setPersonal(personal);
            pessoa.setCref(personalInput.getCref());
            pessoa.setEmail(personalInput.getEmail());
            pessoa.setNome(personalInput.getNome());

            pessoa = pessoaRepo.salvarPessoa(pessoa);

            usuario.setPessoa(pessoa);
            usuario.setLogin(personalInput.getEmail());
            usuario.setSenha(personalInput.getSenha());
            usuario.setFoto(personalInput.getFoto());

            usuarioRepo.salvarUsuario(usuario);
        }else{
            personal = personalRepo.atualizarPersonal(personal);

            Pessoa pessoa = new Pessoa();
            Usuario usuario = new Usuario();

            pessoa = pessoaRepo.obterPessoaPorPersonal(personalInput.getId());

            usuario.setPessoa(pessoa);
            usuario.setFoto(personalInput.getFoto());
            usuario.setSenha(personalInput.getSenha());

            usuarioRepo.atualizarUsuario(usuario);
        }
        return personal;
    }
      
}
