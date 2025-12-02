import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PixGUI extends JFrame {

    public PixGUI() {
        setTitle("Sistema de Verificação de Golpes - PIX");
        setSize(1000, 600);  // 👉 Tamanho maior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com margem
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        // Título
        JLabel titleLabel = new JLabel("Verificação de Segurança do PIX", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(new EmptyBorder(10, 10, 20, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de inputs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font inputFont = new Font("Arial", Font.PLAIN, 18);

        // Valor
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Valor do PIX (R$):"), gbc);

        gbc.gridx = 1;
        JTextField valorField = new JTextField(20);
        valorField.setFont(inputFont);
        formPanel.add(valorField, gbc);

        // Chave PIX
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Chave PIX destino:"), gbc);

        gbc.gridx = 1;
        JTextField chaveField = new JTextField(20);
        chaveField.setFont(inputFont);
        formPanel.add(chaveField, gbc);

        // Descrição (agora MUITO maior)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        formPanel.add(new JLabel("Descrição da transferência:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        JTextArea descricaoArea = new JTextArea(8, 20);  // 👉 altura aumentada
        descricaoArea.setFont(inputFont);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(descricaoArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));  // 👉 tamanho maior

        formPanel.add(scrollPane, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        // Botão
        JButton verificarButton = new JButton("Verificar Segurança");
        verificarButton.setFont(new Font("Arial", Font.BOLD, 20));
        verificarButton.setBackground(new Color(33, 150, 243));
        verificarButton.setForeground(Color.WHITE);
        verificarButton.setFocusPainted(false);
        verificarButton.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Ação do botão
        verificarButton.addActionListener(e -> {
            String valor = valorField.getText();
            String chave = chaveField.getText();
            String descricao = descricaoArea.getText();

            if (valor.isEmpty() || chave.isEmpty() || descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Regra simples de golpe
            boolean suspeito = descricao.toLowerCase().contains("urgente")
                    || descricao.toLowerCase().contains("agora")
                    || descricao.toLowerCase().contains("imediato");

            if (suspeito) {
                JOptionPane.showMessageDialog(this,
                        "⚠️ POSSÍVEL GOLPE DETECTADO!\n\n" +
                                "A descrição contém termos associados a tentativas de fraude.",
                        "Alerta de Segurança", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "✔ PIX considerado seguro.\nNenhum padrão suspeito encontrado.",
                        "Seguro", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Painel do botão centralizado
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(verificarButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PixGUI().setVisible(true));
    }
}
