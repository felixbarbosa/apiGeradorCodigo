package com.geradorcodigo.geradorcodigo.Repository;
import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Carga;

public interface CargaRepository {

    List<Carga> obterCargasPorAlunoPorExercicio(int aluno, int exercicio);

    Carga obterUltimaCargaPorAlunoPorExercicio(int aluno, int exercicio);

    Carga obterPrimeiraCargaPorAlunoPorExercicio(int aluno, int exercicio);

    Carga salvarCarga(Carga carga);

    //Aluno atualizarAluno(Aluno aluno);
}
