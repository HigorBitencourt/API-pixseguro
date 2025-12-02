package com.projeto.pix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class TelaPrincipal extends JFrame {

    private JTextField campoChave;
    private JTextField campoValor;
    private JLabel resultadoLabel;

    public TelaPrincipal() {
        setTitle("💸 Sistema Anti-Golpe PIX");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Painel de fundo com gradiente
        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(60, 100, 200),
                        0, getHeight(), new Color(20, 40, 120));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelFundo.setLayout(null);

        // "Card" branco central
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(60, 40, 500, 350);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // TÍTULO
        JLabel titulo = new JLabel("Analisador de PIX", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(60, 5, 200, 30);
        titulo.setForeground(new Color(0, 70, 150));

        // Campo de chave
        JLabel lblChave = new JLabel("Chave PIX:");
        lblChave.setFont(new Font("Arial", Font.BOLD, 14));
        lblChave.setBounds(20, 50, 120, 20);

        campoChave = new JTextField();
        campoChave.setBounds(20, 70, 280, 28);
        campoChave.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        // Campo valor
        JLabel lblValor = new JLabel("Valor (R$):");
        lblValor.setFont(new Font("Arial", Font.BOLD, 14));
        lblValor.setBounds(20, 105, 120, 20);

        campoValor = new JTextField();
        campoValor.setBounds(20, 125, 280, 28);
        campoValor.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        // Botão estilizado
        JButton botao = new JButton("Analisar PIX");
        botao.setBounds(80, 165, 160, 35);
        botao.setFocusPainted(false);
        botao.setBackground(new Color(40, 100, 200));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBorder(BorderFactory.createEmptyBorder());
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Resultado
        resultadoLabel = new JLabel("", SwingConstants.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 15));
        resultadoLabel.setBounds(20, 200, 500, 30);
        resultadoLabel.setForeground(new Color(0, 0, 0));

        // Ação do botão
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analisar();
            }
        });

        // Montagem
        card.add(titulo);
        card.add(lblChave);
        card.add(campoChave);
        card.add(lblValor);
        card.add(campoValor);
        card.add(botao);
        card.add(resultadoLabel);

        painelFundo.add(card);
        add(painelFundo);
        setVisible(true);
    }

    private void analisar() {
        String chave = campoChave.getText();
        double valor;

        try {
            valor = Double.parseDouble(campoValor.getText());
        } catch (Exception e) {
            resultadoLabel.setText("⚠ Valor inválido!");
            resultadoLabel.setForeground(Color.RED);
            return;
        }

        TransacaoPix transacao = new TransacaoPix(chave, valor);
        String resultado = PixService.analisarTransacao(transacao);

        if (resultado.contains("ALERTA")) {
            resultadoLabel.setForeground(Color.RED);
        } else if (resultado.contains("ATENÇÃO")) {
            resultadoLabel.setForeground(Color.ORANGE);
        } else {
            resultadoLabel.setForeground(new Color(0, 150, 0));
        }

        resultadoLabel.setText(resultado);
    }
}
