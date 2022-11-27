package br.univille.poo.app.ui.listar;

import br.univille.poo.app.entidade.Tarefa;
import br.univille.poo.app.servico.ListarTarefas;
import br.univille.poo.app.ui.cadastrar.CadastroTarefasView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaDeTarefasView extends JFrame {

    private JPanel listaPanel;
    private JPanel root;
    private ListarTarefas listarTarefasServico;

    JButton cadastrarBotao = new JButton();
    private ListaDeTarefasViewListener listener;

    public ListaDeTarefasView(){
        setTitle("Lista");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,600);
        listarTarefasServico = new ListarTarefas();
        configurarJanela();
    }

    private void configurarJanela() {
        listaPanel = new JPanel();
        root = new JPanel();
        root.setLayout(new BorderLayout());
        listaPanel.setLayout(new BoxLayout(listaPanel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listaPanel);
        popularLista();

        root.add(obterHeader(), BorderLayout.NORTH);
        root.add(scrollPane, BorderLayout.CENTER);

        add(root);
    }

    private JPanel obterHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel.add(new JLabel("Tarefas"));
        cadastrarBotao = new JButton("Adicionar Tarefa");
        panel.add(cadastrarBotao);

        cadastrarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null) {
                    try {
                        listener.onAdicionar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        panel.setPreferredSize(new Dimension(350,40));
        panel.setSize(290,20);
        return panel;
    }

    private void popularLista(){
        for(Tarefa tarefa : listarTarefasServico.obterTodos()){
            listaPanel.add(criarItemDaLista(tarefa));
        }
    }

    private JPanel criarItemDaLista(Tarefa item){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(item.isConcluido());
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(listener != null) {
                    String id = String.valueOf(item.getId());
                    try {
                        listener.onConcluir(id, item.isConcluido());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        JLabel labelSituacao = new JLabel("Finalizado: ");
        JLabel label = new JLabel(item.getDescricao());
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(listener != null) {
                    String id = String.valueOf(item.getId());
                    try {
                        listener.onEditar(id);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        panel.add(labelSituacao);
        panel.add(checkBox);
        panel.add(label);
        panel.add(btnEditar);
        panel.setPreferredSize(new Dimension(290,60));
        return panel;
    }

    public void addListener(ListaDeTarefasView.ListaDeTarefasViewListener listener){
        this.listener = listener;
    }

    public interface ListaDeTarefasViewListener {
        void onAdicionar() throws Exception;

        void onConcluir(String id, boolean atual) throws Exception;

        void onEditar(String id) throws Exception;
    }
}
