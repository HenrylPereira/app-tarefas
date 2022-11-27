package br.univille.poo.app.ui.listar;

import br.univille.poo.app.servico.CriarTarefa;
import br.univille.poo.app.servico.TarefaDeleteService;
import br.univille.poo.app.servico.TarefaGetByIdService;
import br.univille.poo.app.ui.cadastrar.CadastroTarefasController;
import br.univille.poo.app.ui.cadastrar.CadastroTarefasModel;
import br.univille.poo.app.ui.cadastrar.CadastroTarefasView;

public class ListaDeTarefasController implements ListaDeTarefasView.ListaDeTarefasViewListener {

    private ListaDeTarefasView view;
    private ListaDeTarefasModel model;

    public ListaDeTarefasController(ListaDeTarefasView view, ListaDeTarefasModel model){
        this.view = view;
        this.model = model;
        this.view.addListener(this);
    }

    public void exibirView(){
        view.setVisible(true);
    }

    @Override
    public void onAdicionar() {
        view.setVisible(false);
        CadastroTarefasModel modelTarefas = new CadastroTarefasModel(new CriarTarefa(), new TarefaGetByIdService(), new TarefaDeleteService());
        CadastroTarefasView viewTarefas = new CadastroTarefasView();
        CadastroTarefasController controlTarefas = new CadastroTarefasController(modelTarefas,viewTarefas);
        controlTarefas.exibirView();
    }

    @Override
    public void onConcluir(String id, boolean atual) throws Exception {
        model.concluirTarefa(id, atual);
    }

    @Override
    public void onEditar(String id) throws Exception {
        view.setVisible(false);
        CadastroTarefasModel modelTarefas = new CadastroTarefasModel(new CriarTarefa(), new TarefaGetByIdService(), new TarefaDeleteService());
        CadastroTarefasView viewTarefas = new CadastroTarefasView();
        CadastroTarefasController controlTarefas = new CadastroTarefasController(modelTarefas,viewTarefas);
        controlTarefas.editar(id);
        controlTarefas.exibirView();
    }
}
