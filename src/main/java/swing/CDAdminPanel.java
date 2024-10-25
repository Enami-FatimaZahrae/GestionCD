package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CDAdminPanel extends JPanel {
    private JTextField titreField;
    private JTextField auteurField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JList<String> cdList;
    private DefaultListModel<String> listModel;

    public CDAdminPanel() {
        setLayout(new BorderLayout());

        // Initialisation des champs de texte et des boutons
        titreField = new JTextField(15);
        auteurField = new JTextField(15);
        addButton = new JButton("Ajouter CD");
        editButton = new JButton("Modifier CD");
        deleteButton = new JButton("Supprimer CD");

        // Modèle de liste pour afficher les CDs
        listModel = new DefaultListModel<>();
        cdList = new JList<>(listModel);

        // Panel pour les champs d'entrée
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Titre:"));
        inputPanel.add(titreField);
        inputPanel.add(new JLabel("Auteur:"));
        inputPanel.add(auteurField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);

        // Ajouter les composants au panneau principal
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(cdList), BorderLayout.CENTER);

        // Action pour le bouton d'ajout
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                if (!titre.isEmpty() && !auteur.isEmpty()) {
                    listModel.addElement(titre + " - " + auteur);
                    titreField.setText("");
                    auteurField.setText("");
                } else {
                    JOptionPane.showMessageDialog(CDAdminPanel.this, "Veuillez remplir les champs Titre et Auteur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour le bouton de modification
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cdList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String titre = titreField.getText();
                    String auteur = auteurField.getText();
                    if (!titre.isEmpty() && !auteur.isEmpty()) {
                        listModel.set(selectedIndex, titre + " - " + auteur);
                        titreField.setText("");
                        auteurField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(CDAdminPanel.this, "Veuillez remplir les champs Titre et Auteur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(CDAdminPanel.this, "Veuillez sélectionner un CD à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour le bouton de suppression
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cdList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(CDAdminPanel.this, "Veuillez sélectionner un CD à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
