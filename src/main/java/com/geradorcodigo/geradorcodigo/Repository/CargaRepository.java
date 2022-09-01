package com.geradorcodigo.geradorcodigo.Repository;
import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Carga;

public interface CargaRepository {

    List<Carga> obterCargasPorAluno(int aluno);

    Carga obterCargaPorAlunoPorExercicio(int aluno, int exercicio);

    Carga salvarCarga(Carga carga);

    //Aluno atualizarAluno(Aluno aluno);
}
