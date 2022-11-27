package br.univille.poo.app.ui.listar;

import br.univille.poo.app.entidade.Tarefa;
import br.univille.poo.app.servico.ListarTarefas;
import br.univille.poo.app.servico.TarefaConcluirService;

public class ListaDeTarefasModel {
    private final TarefaConcluirService tarefaConcluirService;
    private Tarefa tarefa;
    private ListarTarefas listarTarefas;

    public ListaDeTarefasModel(ListarTarefas listarTarefas, TarefaConcluirService tarefaConcluirService){
        this.listarTarefas = listarTarefas;
        this.tarefaConcluirService = tarefaConcluirService;
    }

    public void concluirTarefa(String id, boolean atual) throws Exception {
        this.tarefaConcluirService.concluir(id,atual);
    }
}
