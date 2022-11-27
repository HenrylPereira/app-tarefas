package br.univille.poo.app;

import br.univille.poo.app.persistencia.CriarTabelas;
import br.univille.poo.app.servico.ListarTarefas;
import br.univille.poo.app.servico.TarefaConcluirService;
import br.univille.poo.app.ui.listar.ListaDeTarefasController;
import br.univille.poo.app.ui.listar.ListaDeTarefasModel;
import br.univille.poo.app.ui.listar.ListaDeTarefasView;

public class Main {

    public static void main(String[] args) {
        CriarTabelas.criarTabelas();

        ListaDeTarefasModel model = new ListaDeTarefasModel(new ListarTarefas(), new TarefaConcluirService());
        ListaDeTarefasView view = new ListaDeTarefasView();
        ListaDeTarefasController control = new ListaDeTarefasController(view, model);
        control.exibirView();
    }

}
