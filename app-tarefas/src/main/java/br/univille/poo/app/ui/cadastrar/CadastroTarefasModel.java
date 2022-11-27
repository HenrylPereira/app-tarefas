package br.univille.poo.app.ui.cadastrar;

import br.univille.poo.app.entidade.Tarefa;
import br.univille.poo.app.servico.CriarTarefa;
import br.univille.poo.app.servico.TarefaDeleteService;
import br.univille.poo.app.servico.TarefaGetByIdService;

public class CadastroTarefasModel {

    private final TarefaDeleteService tarefaDeleteService;
    private Tarefa tarefa;
    private CriarTarefa createService;

    private TarefaGetByIdService tarefaGetByIdService;

    public CadastroTarefasModel(CriarTarefa createService, TarefaGetByIdService tarefaGetByIdService, TarefaDeleteService tarefaDeleteService){
        this.createService = createService;
        this.tarefaGetByIdService = tarefaGetByIdService;
        this.tarefaDeleteService = tarefaDeleteService;
    }

    public void adicionarTarefa(Tarefa tarefa) throws Exception {
        this.createService.criar(tarefa);
    }

    public void atualizarTarefa(String id, Tarefa tarefa) throws Exception {
        this.createService.criar(tarefa);
    }

    public void excluirTarefa(String id) throws Exception {
        this.tarefaDeleteService.delete(id);
    }

    public Tarefa getById(String id) throws Exception {
        return this.tarefaGetByIdService.getById(id);
    }

}
