package com.geradorcodigo.geradorcodigo.Graphql;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.geradorcodigo.geradorcodigo.Model.Aluno;
import com.geradorcodigo.geradorcodigo.Model.Anamnese;
import com.geradorcodigo.geradorcodigo.Model.AnamneseInput;
import com.geradorcodigo.geradorcodigo.Repository.AnamneseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnamneseGraphQl implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private AnamneseRepository anamneseRepo;


    public List<Anamnese> obterAnamnesePorAluno(int alunoId){

        List<Anamnese> lista = anamneseRepo.obterAnamnesePorAluno(alunoId);
        return lista;    
    }

    public Anamnese salvarAnamnese(AnamneseInput anamneseInput){
 
        Anamnese anamnese = new Anamnese();
        Aluno aluno = new Aluno();

        aluno.setId(anamneseInput.getAluno());
        anamnese.setAluno(aluno);

        anamnese.setAlteracaoCardiaca(anamneseInput.getAlteracaoCardiaca());
        anamnese.setAtividadeFisica(anamneseInput.getAtividadeFisica());
        anamnese.setBebidaAlcoolica(anamneseInput.getBebidaAlcoolica());
        anamnese.setCirurgia(anamneseInput.getCirurgia());
        anamnese.setColesterol(anamneseInput.getColesterol());
        anamnese.setDiabetes(anamneseInput.getDiabetes());
        anamnese.setDieta(anamneseInput.getDieta());
        anamnese.setDores(anamneseInput.getDores());
        anamnese.setFumante(anamneseInput.getFumante());
        anamnese.setHipertenso(anamneseInput.getHipertenso());
        anamnese.setId(anamneseInput.getId());
        anamnese.setMedicamento(anamneseInput.getMedicamento());
        anamnese.setObjetivo(anamneseInput.getObjetivo());
        anamnese.setObservacoes(anamneseInput.getObservacoes());
        anamnese.setProblemaOrtopedico(anamneseInput.getProblemaOrtopedico());
        anamnese.setPulmonar(anamneseInput.getPulmonar());
        anamnese.setRefeicoes(anamneseInput.getRefeicoes());
        anamnese.setSono(anamneseInput.getSono());
        anamnese.setSuplementacao(anamneseInput.getSuplementacao());

        if(anamnese.getId() == 0){
            anamneseRepo.deletarAnamnesePorAluno(anamneseInput.getAluno());
            anamnese = anamneseRepo.salvarAnamnese(anamnese);
        }
        return anamnese;
    }
      
}
