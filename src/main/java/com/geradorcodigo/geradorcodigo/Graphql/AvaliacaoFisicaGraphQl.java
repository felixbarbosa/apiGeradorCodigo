package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.AvaliacaoFisica;
import com.geradorcodigo.geradorcodigo.Model.AvaliacaoFisicaInput;
import com.geradorcodigo.geradorcodigo.Repository.AvaliacaoFisicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoFisicaGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoRepo;


    public List<AvaliacaoFisica> obterAvaliacaoPorAluno(int alunoId){

        List<AvaliacaoFisica> lista = avaliacaoRepo.obterAvaliacaoPorAluno(alunoId);
        return lista;    
    }

    public AvaliacaoFisica salvarAvaliacao(AvaliacaoFisicaInput avaliacaoInput){
 
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Aluno aluno = new Aluno();

        aluno.setId(avaliacaoInput.getAluno());
        avaliacaoFisica.setAluno(aluno);

        avaliacaoFisica.setAbdome(avaliacaoInput.getAbdome());
        avaliacaoFisica.setAltura(avaliacaoInput.getAltura());
        avaliacaoFisica.setAnteBraco(avaliacaoInput.getAnteBraco());
        avaliacaoFisica.setBiceps(avaliacaoInput.getBiceps());
        avaliacaoFisica.setCintura(avaliacaoInput.getCintura());
        avaliacaoFisica.setCoxa(avaliacaoInput.getCoxa());
        avaliacaoFisica.setData(avaliacaoInput.getData());
        avaliacaoFisica.setFotoCostas(avaliacaoInput.getFotoCostas());
        avaliacaoFisica.setFotoFrente(avaliacaoInput.getFotoFrente());
        avaliacaoFisica.setFotoLado(avaliacaoInput.getFotoLado());
        avaliacaoFisica.setId(avaliacaoInput.getId());
        avaliacaoFisica.setIdate(avaliacaoInput.getIdate());
        avaliacaoFisica.setObjetivo(avaliacaoInput.getObjetivo());
        avaliacaoFisica.setPeitoral(avaliacaoInput.getPeitoral());
        avaliacaoFisica.setPeso(avaliacaoInput.getPeso());
        avaliacaoFisica.setQuadril(avaliacaoInput.getQuadril());

        if(avaliacaoFisica.getId() == 0){
            avaliacaoFisica = avaliacaoRepo.salvarAvaliacao(avaliacaoFisica);
        }
        return avaliacaoFisica;
    }
      
}
