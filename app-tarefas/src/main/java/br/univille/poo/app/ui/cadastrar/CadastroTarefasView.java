package br.univille.poo.app.ui.cadastrar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroTarefasView extends JFrame {
    JPanel panel = new JPanel();
    private String[] opcoes = {"Fácil né","Médil","Difícil"};
    boolean editar;
    JComboBox opcoesComboBox = new JComboBox<>();

    JButton btnExcluir = new JButton();
    JTextArea tarefaTextArea = new JTextArea();
    JLabel tarefasLabel = new JLabel();
    JLabel prioridadeLabel = new JLabel();
    JCheckBox finalizadoCheck = new JCheckBox();
    JButton calcularCancelar = new JButton();
    JButton calcularSalvar = new JButton();
    private CadastroTarefasViewListener listener;

    public CadastroTarefasView(){
        setTitle("Cadastro de Tarefas");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configurarJanela();
        setVisible(true);
    }

    public void addListener(CadastroTarefasViewListener listener){
        this.listener = listener;
    }

    public void configurarJanela(){
        tarefasLabel = new JLabel();
        prioridadeLabel = new JLabel();
        tarefaTextArea = new JTextArea();

        tarefasLabel.setText("Tarefa: ");
        prioridadeLabel.setText("Prioridade: ");

        tarefaTextArea = new JTextArea();
        tarefaTextArea.setColumns(25);
        tarefaTextArea.setRows(3);
        tarefaTextArea.setLineWrap(true);
        tarefaTextArea.setBorder(BorderFactory.createLineBorder(Color.black));

        opcoesComboBox = new JComboBox<>(opcoes);
        opcoesComboBox.setPreferredSize( new Dimension(260,30));

        finalizadoCheck = new JCheckBox("Finalizado");
        finalizadoCheck.setPreferredSize( new Dimension(300,30));

        calcularCancelar = new JButton("Cancelar");
        calcularCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null) {
                    try {
                        listener.onCancelar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        calcularSalvar = new JButton("Salvar");
        calcularSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null) {
                    String tarefaText = tarefaTextArea.getText();
                    String opcaoCombo = opcoesComboBox.getSelectedItem().toString();
                    boolean checkText = finalizadoCheck.isSelected();
                    try {
                        listener.onSalvar(tarefaText, opcaoCombo, checkText);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        panel.add(tarefasLabel);
        panel.add(tarefaTextArea);
        panel.add(prioridadeLabel);
        panel.add(opcoesComboBox);
        panel.add(finalizadoCheck);
        panel.add(calcularCancelar);
        panel.add(calcularSalvar);
        add(panel);
    }

    public void addExcluir(String id){
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener != null) {
                    try {
                        listener.onExcluir(id);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        panel.add(btnExcluir);
    }

    interface CadastroTarefasViewListener{
        void onSalvar(String tarefaText, String opcaoCombo, boolean checkText) throws Exception;

        void onExcluir(String id) throws Exception;

        void onCancelar() throws Exception;
    }


}