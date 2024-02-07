import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Abonne extends JFrame implements ActionListener {
    JLabel lb1, lb2, lbtitre;
    JTextField tf1, tf2;
    JButton insertBtn, deleteBtn, researchBtn, modBtn, refreshBtn;
    JTable jt;
    JScrollPane js;

    ServerInterface server;

    public Abonne() {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            // Look up the remote object
            server = (ServerInterface) registry.lookup("RMIServer");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        this.setTitle("Gestion Abonnés");
        this.setSize(750, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setForeground(Color.white);
        JMenuItem i1 = new JMenuItem("Gestion des livres");
        JMenuItem i2 = new JMenuItem("Gestion des emprunts");
        JMenuItem i5 = new JMenuItem("Requetes");
        JMenuItem i4 = new JMenuItem("Deconnexion");
        menu.add(i1);
        menu.add(i2);
        menu.add(i5);
        menu.add(i4);

        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        i5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        i4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        menuBar.add(menu);
        menuBar.setPreferredSize(new Dimension(0, 30));
        UIManager.put("MenuBar.background", new Color(51, 153, 255));
        this.setJMenuBar(menuBar);
        /* menu */

        this.setJMenuBar(menuBar);

        JPanel pn = new JPanel();
        pn.setLayout(null);
        pn.setBackground(new Color(255, 245, 238));
        add(pn);
        //titre
        lbtitre = new JLabel("Enregistrement des Abonnés");
        lbtitre.setBounds(10, 10, 300, 30);
        lbtitre.setForeground(new Color(128, 128, 0));
        lbtitre.setFont(new Font("Arial", Font.BOLD, 17));
        pn.add(lbtitre);
        //label
        lb1 = new JLabel("Identifiant:");
        lb1.setBounds(30, 50, 100, 30);
        pn.add(lb1);
        tf1 = new JTextField();
        tf1.setBounds(100, 50, 100, 30);
        pn.add(tf1);
        //
        lb2 = new JLabel("Nom:");
        lb2.setBounds(30, 90, 100, 30);
        pn.add(lb2);

        tf2 = new JTextField();
        tf2.setBounds(100, 90, 200, 30);
        pn.add(tf2);
        //
        pn.add(tf2);
        //button
        insertBtn = new JButton("Insertion");
        insertBtn.setBounds(30, 130, 120, 40);
        insertBtn.setForeground(Color.white);
        insertBtn.setBackground(new Color(255, 69, 0));
        insertBtn.addActionListener(this);
        pn.add(insertBtn);

        deleteBtn = new JButton("Suppression");
        deleteBtn.setBounds(180, 130, 120, 40);
        deleteBtn.setForeground(Color.white);
        deleteBtn.setBackground(new Color(255, 69, 0));
        deleteBtn.addActionListener(this);
        pn.add(deleteBtn);

        researchBtn = new JButton("Recherche");
        researchBtn.setBounds(200, 50, 100, 30);
        researchBtn.setForeground(Color.white);
        researchBtn.setBackground(new Color(255, 69, 0));
        researchBtn.addActionListener(this);
        pn.add(researchBtn);


        modBtn = new JButton("Modification");
        modBtn.setBounds(30, 190, 120, 40);
        modBtn.setForeground(Color.white);
        modBtn.setBackground(new Color(255, 69, 0));
        modBtn.addActionListener(this);
        pn.add(modBtn);

        refreshBtn = new JButton("Actualiser");
        refreshBtn.setBounds(180, 190, 120, 40);
        refreshBtn.addActionListener(this);
        refreshBtn.setForeground(Color.white);
        refreshBtn.setBackground(new Color(255, 69, 0));
        pn.add(refreshBtn);


        DefaultTableModel df = new DefaultTableModel();
        init();
        df.addColumn("IDENTIFIANT");
        df.addColumn("NOM & PRENOM");
        jt.setModel(df);
        pn.add(js);

        // SELECT ALL
        try {
            List<List<String>> result = server.getAllString();

            for (List<String> ligne : result) {
                df.addRow(new Object[]{ligne.get(0), ligne.get(1)});
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void init() {
        jt = new JTable();
        js = new JScrollPane();
        js.setViewportView(jt);
        js.setBounds(310, 10, 350, 300);
    }

    @Override

    //JButton insertBtn, deleteBtn,  researchBtn, modBtn, refreshBtn, abonne, bpret;
    public void actionPerformed(ActionEvent e) {

        //TODO: INSERTABONNE
        if (e.getSource() == insertBtn) {
            String a, b;
            a = tf1.getText();
            b = tf2.getText();
            try {
                String res = server.addUser(a, b);
                if (res != null) JOptionPane.showMessageDialog(this, "Insertion reussie!");
                else JOptionPane.showMessageDialog(this, "Insertion echouée!");
            } catch (Exception exp) {
                System.out.println(exp);
                JOptionPane.showMessageDialog(this, "Erreur,insertion impossible!", null, JOptionPane.ERROR_MESSAGE);
            }
        }

        //TODO: DELETE ABONNE
        if (e.getSource() == deleteBtn) {
            String a;
            a = tf1.getText();
            try {
                String res = server.deleteUserById(a);
                if (res != null) JOptionPane.showMessageDialog(this, "Suppression reussie!");
                else JOptionPane.showMessageDialog(this, "Suppression echouée!");
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Suppression Failed!");
                throw new RuntimeException(ex);
            }
        }

        //TODO: SELECT ABONNE BY ID
        if (e.getSource() == researchBtn) {
            String a;
            a = tf1.getText();
            String result;
            try {
                result = server.getUserById(a);
                if (result == null) JOptionPane.showMessageDialog(this, "Introuvable!");
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Recherche Failed!");
                throw new RuntimeException(ex);
            }
            tf2.setText(result);
        }

        //REFRESH LIST
        if (e.getSource() == refreshBtn) {
            dispose();
            Abonne ab = new Abonne();
            ab.setVisible(true);


        }

        //TODO UPDATE ABONNE
        if (e.getSource() == modBtn) {
            String a, b;
            a = tf1.getText();
            b = tf2.getText();
            try {
                String res = server.updateUser(a, b);
                if (res != null) JOptionPane.showMessageDialog(this, "Màj reussie!");
                else JOptionPane.showMessageDialog(this, "Màj echouée!");
            } catch (Exception exp) {
                System.out.println(exp);
            }

        }
        //aller vers l'interface graphique de gestion des livres

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {

            Abonne ab = new Abonne();
            ab.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
