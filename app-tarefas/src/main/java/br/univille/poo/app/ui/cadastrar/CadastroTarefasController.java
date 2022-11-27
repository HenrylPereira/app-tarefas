package br.univille.poo.app.ui.cadastrar;

import br.univille.poo.app.entidade.Tarefa;
import br.univille.poo.app.servico.ListarTarefas;
import br.univille.poo.app.servico.TarefaConcluirService;
import br.univille.poo.app.ui.listar.ListaDeTarefasController;
import br.univille.poo.app.ui.listar.ListaDeTarefasModel;
import br.univille.poo.app.ui.listar.ListaDeTarefasView;

import javax.swing.*;

public class CadastroTarefasController implements CadastroTarefasView.CadastroTarefasViewListener {

    private CadastroTarefasView view;
    private CadastroTarefasModel model;

    private boolean editar;
    private String id;

    public CadastroTarefasController(CadastroTarefasModel model, CadastroTarefasView view){
        this.view = view;
        this.model = model;
        this.view.addListener(this);
    }



    public void exibirView(){
        view.setVisible(true);
    }

    public void editar(String id) throws Exception {
        this.editar = true;
        Tarefa tarefa = model.getById(id);
        view.tarefaTextArea.setText(tarefa.getDescricao());
        view.opcoesComboBox.setSelectedItem(tarefa.getPrioridade());
        view.finalizadoCheck.setSelected(tarefa.isConcluido());
        view.addExcluir(id);
    }



    @Override
    public void onSalvar(String tarefaText, String opcaoCombo, boolean checkText) throws Exception {
        Tarefa tarefaC = new Tarefa();
        tarefaC.setDescricao(tarefaText);
        tarefaC.setConcluido(checkText);
        tarefaC.setPrioridade(opcaoCombo);

        if(editar){
            model.atualizarTarefa(this.id, tarefaC);
        }else{
            model.adicionarTarefa(tarefaC);
        }
        this.openLista();
    }

    @Override
    public void onExcluir(String id) throws Exception {
        model.excluirTarefa(id);
        this.openLista();
    }

    @Override
    public void onCancelar() throws Exception {
        this.openLista();
    }


    public void openLista(){
        view.setVisible(false);
        ListaDeTarefasModel model = new ListaDeTarefasModel(new ListarTarefas(), new TarefaConcluirService());
        ListaDeTarefasView view = new ListaDeTarefasView();
        ListaDeTarefasController control = new ListaDeTarefasController(view, model);
        control.exibirView();
    }
}
