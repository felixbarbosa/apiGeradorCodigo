package com.geradorcodigo.geradorcodigo.Repository;

import java.util.List;

import com.geradorcodigo.geradorcodigo.Model.Objetivo;

public interface ObjetivoRepository {
    
    List<Objetivo> obterObjetivos();

    Objetivo obterObjetivoPorId(int id);
}
