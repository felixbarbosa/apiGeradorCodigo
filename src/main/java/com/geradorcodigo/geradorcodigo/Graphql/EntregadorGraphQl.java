package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Entregador;
import com.geradorcodigo.geradorcodigo.Model.EntregadorInput;
import com.geradorcodigo.geradorcodigo.Repository.EntregadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntregadorGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private EntregadorRepository entregadorRepo;


    public List<Entregador> entregadores(){

        List<Entregador> lista = entregadorRepo.obterTodos();
        return lista;    
    }

    public Entregador salvarEntregador(EntregadorInput entregadorInput){
 
        Entregador entregador= new Entregador();
        entregador.setId(entregadorInput.getId());
        entregador.setCodigo(entregadorInput.getCodigo());
        entregador.setData(entregadorInput.getData());
        entregador.setHora(entregadorInput.getHora());
        entregador.setLocal(entregadorInput.getLocal());
        entregador.setTurno(entregadorInput.getTurno());
        entregador.setVisto(entregadorInput.getVisto());

        if(entregador.getId() == 0){
            entregador = entregadorRepo.salvar(entregador);
        }else{
            entregador = entregadorRepo.atualizar(entregador);
        }
        return entregador;
    }
      
}
