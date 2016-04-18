package br.edu.ifpb.Classes;

import br.edu.ifpb.Classes.Pessoa;

import java.util.List;

public interface ProcurarPessoa {

    void backProcurar(List<Pessoa> names);

    void errorProcurar(String error);
}
