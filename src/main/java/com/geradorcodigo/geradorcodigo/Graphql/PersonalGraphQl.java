package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Personal;
import com.geradorcodigo.geradorcodigo.Model.PersonalInput;
import com.geradorcodigo.geradorcodigo.Repository.PersonalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonalGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private PersonalRepository personalRepo;


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

        if(personal.getId() == 0){
            personal = personalRepo.salvarPersonal(personal);
        }else{
            personal = personalRepo.atualizarPersonal(personal);
        }
        return personal;
    }
      
}
