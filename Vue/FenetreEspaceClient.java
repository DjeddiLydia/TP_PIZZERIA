package Vue;


import Controller.ControllerFenetreClient;
import javax.swing.*;
import java.awt.*;
import Model.* ;
public class FenetreEspaceClient extends JFrame {

    private Pizzeria pizzeria;

    private JTextField numeroClientTextField = new JTextField("0000000000");

    private JTextField incrementSoldeTextField = new JTextField("0");

    private JLabel soldeClientLabel = new JLabel("0");

    private JButton incrementerSoldeButton = new JButton("Recharger");

    private JButton consulterClientButton = new JButton("Consulter");

    private JList<String> commandesList = new JList<>();

    private DefaultListModel<String> commandesListModel = new DefaultListModel<>();



    public FenetreEspaceClient(Pizzeria piz) {

        super("Espace Client");

        this.pizzeria = piz;


        // Set font and colors

        Font font = new Font("Arial", Font.BOLD, 14);
        Color bgColor = new Color(230, 230, 250); // Lavender
        Color panelBgColor = new Color(240, 248, 255); // Alice Blue
        Color buttonColor = new Color(123, 104, 238); // Medium Slate Blue
        Color buttonTextColor = Color.WHITE;
        Color labelColor = new Color(72, 61, 139); // Dark Slate Blue

        // Apply styles to components

        numeroClientTextField.setFont(font);
        numeroClientTextField.setBackground(Color.WHITE);
        numeroClientTextField.setForeground(Color.BLACK);
        numeroClientTextField.setBorder(BorderFactory.createLineBorder(labelColor));

        incrementSoldeTextField.setFont(font);
        incrementSoldeTextField.setBackground(Color.WHITE);
        incrementSoldeTextField.setForeground(Color.BLACK);
        incrementSoldeTextField.setBorder(BorderFactory.createLineBorder(labelColor));

        soldeClientLabel.setFont(font);
        soldeClientLabel.setForeground(labelColor);

        incrementerSoldeButton.setFont(font);
        incrementerSoldeButton.setBackground(buttonColor);
        incrementerSoldeButton.setForeground(buttonTextColor);
        incrementerSoldeButton.setBorder(BorderFactory.createLineBorder(labelColor));

        consulterClientButton.setFont(font);
        consulterClientButton.setBackground(buttonColor);
        consulterClientButton.setForeground(buttonTextColor);
        consulterClientButton.setBorder(BorderFactory.createLineBorder(labelColor));


        commandesList.setFont(font);
        commandesList.setBackground(Color.WHITE);
        commandesList.setForeground(Color.BLACK);
        commandesList.setBorder(BorderFactory.createLineBorder(labelColor));

        // Create panels
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(bgColor);

        JPanel saisieEtConsulterPanel = new JPanel(new BorderLayout());
        saisieEtConsulterPanel.setBackground(panelBgColor);
        JLabel numeroClientLabel = new JLabel("Numéro du client: ");
        numeroClientLabel.setForeground(labelColor);
        saisieEtConsulterPanel.add(numeroClientLabel, BorderLayout.WEST);
        saisieEtConsulterPanel.add(numeroClientTextField, BorderLayout.CENTER);

        JPanel consulterButtonPanel = new JPanel(new BorderLayout());
        consulterButtonPanel.setBackground(panelBgColor);
        consulterButtonPanel.add(consulterClientButton, BorderLayout.CENTER);
        JPanel hautPanel = new JPanel(new BorderLayout());
        hautPanel.setBackground(panelBgColor);
        hautPanel.add(saisieEtConsulterPanel, BorderLayout.CENTER);
        hautPanel.add(consulterButtonPanel, BorderLayout.SOUTH);

        JPanel soldePanel = new JPanel(new BorderLayout());
        soldePanel.setBackground(panelBgColor);
        JLabel soldeClientTextLabel = new JLabel("Solde du client: ");
        soldeClientTextLabel.setForeground(labelColor);
        soldePanel.add(soldeClientTextLabel, BorderLayout.NORTH);
        soldePanel.add(soldeClientLabel, BorderLayout.CENTER);

        JPanel videPanel = new JPanel();
        videPanel.setPreferredSize(new Dimension(100, 30));
        videPanel.setBackground(panelBgColor);
        JPanel rechargePanel = new JPanel(new BorderLayout());
        rechargePanel.setBackground(panelBgColor);
        JLabel rechargerLabel = new JLabel("Recharger: ");
        rechargerLabel.setForeground(labelColor);
        rechargePanel.add(rechargerLabel, BorderLayout.NORTH);
        JPanel saisieRechargePanel = new JPanel(new GridLayout(1, 2));

        saisieRechargePanel.setBackground(panelBgColor);
        saisieRechargePanel.add(incrementSoldeTextField);
        saisieRechargePanel.add(incrementerSoldeButton);
        rechargePanel.add(saisieRechargePanel, BorderLayout.CENTER);

        JPanel gauchePanel = new JPanel(new BorderLayout());
        gauchePanel.setBackground(panelBgColor);
        gauchePanel.add(soldePanel, BorderLayout.NORTH);
        gauchePanel.add(videPanel, BorderLayout.CENTER);
        gauchePanel.add(rechargePanel, BorderLayout.SOUTH);

        JScrollPane commandesScrollPane = new JScrollPane(commandesList);
        commandesScrollPane.getViewport().setBackground(panelBgColor);

        JPanel droitePanel = new JPanel(new BorderLayout());
        droitePanel.setBackground(panelBgColor);
        JLabel commandesLabel = new JLabel("Commandes: ");
        commandesLabel.setForeground(labelColor);

        droitePanel.add(commandesLabel, BorderLayout.NORTH);
        droitePanel.add(commandesScrollPane, BorderLayout.CENTER);

        JPanel centrePanel = new JPanel(new GridLayout(1, 2));
        centrePanel.setBackground(panelBgColor);
        centrePanel.add(gauchePanel);
        centrePanel.add(droitePanel);

        panelPrincipal.add(hautPanel, BorderLayout.NORTH);
        panelPrincipal.add(centrePanel, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal);
        ControllerFenetreClient crtl = new ControllerFenetreClient(this, pizzeria);
        incrementerSoldeButton.addActionListener(crtl);
        consulterClientButton.addActionListener(crtl);

        setLocation(600,150);
        pack();
        setVisible(true);
    }
    public JButton getIncrementerButton() {
        return incrementerSoldeButton;
    }
    public JButton getConsulterButton() {
        return consulterClientButton;
    }
    public JLabel getSoldeClientLabel() {
        return soldeClientLabel;
    }
    public JTextField getNumJTextField() {
        return numeroClientTextField;
    }
    public JTextField getSoldeJTextField() {
        return incrementSoldeTextField;
    }
    public JList<String> getCommandesList() {
        return commandesList;
    }
    public DefaultListModel<String> getCommandesListModel() {
        return commandesListModel;
    }

}