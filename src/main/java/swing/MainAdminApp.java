package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAdminApp extends JFrame {
    private CDAdminPanel cdAdminPanel;

    public MainAdminApp() {
        setTitle("Admin - Gestion des CDs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialiser le panneau de gestion des CD
        cdAdminPanel = new CDAdminPanel();

        // Ajouter le panneau de gestion des CD à la fenêtre principale
        add(cdAdminPanel);

        // Créer un menu pour gérer les emprunts
        JMenuBar menuBar = new JMenuBar();
        JMenu gestionMenu = new JMenu("Gestion");

        JMenuItem voirEmpruntsItem = new JMenuItem("Voir les emprunts");
        voirEmpruntsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir un dialogue pour afficher les emprunts en cours
                showEmpruntsDialog();
            }
        });

        gestionMenu.add(voirEmpruntsItem);
        menuBar.add(gestionMenu);
        setJMenuBar(menuBar);
    }

    // Méthode pour afficher une boîte de dialogue avec les emprunts en cours
    private void showEmpruntsDialog() {
        // Exemple d'affichage de message - personnalisez selon les besoins
        JOptionPane.showMessageDialog(this, "Liste des emprunts en cours", "Emprunts", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainAdminApp app = new MainAdminApp();
                app.setVisible(true);
            }
        });
    }
}
