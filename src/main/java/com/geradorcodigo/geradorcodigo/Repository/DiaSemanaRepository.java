package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.DiaSemana;

public interface DiaSemanaRepository {
    
    List<DiaSemana> obterDiasSemana();

    DiaSemana obterDiaSemanaPorId(int id);
}
