package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Musculo;
import com.geradorcodigo.geradorcodigo.Repository.MusculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusculoGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private MusculoRepository musculoRepo;


    public List<Musculo> obterMusculos(){

        List<Musculo> lista = musculoRepo.obterMusculos();
        return lista;    
    }

    public Musculo obterMusculoPorId(int id){

        Musculo musculo = musculoRepo.obterMusculoPorId(id);
        return musculo;    
    }
      
}
