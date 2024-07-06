package iii.unipv.soproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

@SuppressWarnings("serial")
public class Grafica extends JFrame implements EventListener {

    private JFrame f;
    private JPanel panelCucina;
    private JPanel panelCucinaOrdini;
    private JPanel panelCameriere;
    private JPanel panelCameriereOrdini;
    private JPanel panelClienti;
    private JPanel panelClientiOrdini;
    private JPanel panelTavolo;
    private JLabel labelNumeroTavolo;
    private JLabel labelCucinaString;
    private JLabel labelCameriereString;
    private JLabel labelClientiString;
    private JScrollPane scrollPaneCucinaOrdini;
    private JScrollPane scrollPaneCameriereOrdini;
    private JScrollPane scrollPaneClientiOrdini;

    public Grafica() {
        f = new JFrame();
        f.setLocation(0, 0);
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.black);
        f.setLayout(null);

        panelCucina = new JPanel();
        panelCucina.setBackground(Color.cyan);
        panelCucina.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 4), 0);
        panelCucina.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 4);

        labelCucinaString = new JLabel("CUCINA");
        labelCucinaString.setFont(new Font("Arial", Font.BOLD, 30));

        panelCucinaOrdini = new JPanel();
        panelCucinaOrdini.setBackground(Color.white);
        panelCucinaOrdini.setLayout(new BoxLayout(panelCucinaOrdini, BoxLayout.Y_AXIS));

        scrollPaneCucinaOrdini = new JScrollPane(panelCucinaOrdini);
        scrollPaneCucinaOrdini.setLocation(430, 80);
        scrollPaneCucinaOrdini.setSize(680, 136);
        scrollPaneCucinaOrdini.setBorder(BorderFactory.createLineBorder(Color.red, 5));

        panelCameriere = new JPanel();
        panelCameriere.setBackground(Color.BLUE);
        panelCameriere.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) * 78 / 100), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 50);
        panelCameriere.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 5, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 4);

        panelCameriereOrdini = new JPanel();
        panelCameriereOrdini.setBackground(Color.white);
        panelCameriereOrdini.setLayout(new BoxLayout(panelCameriereOrdini, BoxLayout.Y_AXIS));

        scrollPaneCameriereOrdini = new JScrollPane(panelCameriereOrdini);
        scrollPaneCameriereOrdini.setLocation(1237, 80);
        scrollPaneCameriereOrdini.setSize(230, 150);
        scrollPaneCameriereOrdini.setBorder(BorderFactory.createLineBorder(Color.red, 5));

        labelCameriereString = new JLabel("CAMERIERE");
        labelCameriereString.setFont(new Font("Arial", Font.BOLD, 30));

        panelClienti = new JPanel();
        panelClienti.setBackground(Color.BLUE);
        panelClienti.setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 50), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 50);
        panelClienti.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 5, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 4);

        panelClientiOrdini = new JPanel();
        panelClientiOrdini.setBackground(Color.white);
        panelClientiOrdini.setLayout(new BoxLayout(panelClientiOrdini, BoxLayout.Y_AXIS));

        scrollPaneClientiOrdini = new JScrollPane(panelClientiOrdini);
        scrollPaneClientiOrdini.setLocation(70, 80);
        scrollPaneClientiOrdini.setSize(230, 150);
        scrollPaneClientiOrdini.setBorder(BorderFactory.createLineBorder(Color.red, 5));

        labelClientiString = new JLabel("Clienti");
        labelClientiString.setFont(new Font("Arial", Font.BOLD, 30));

        creaPanelTavolo(100, 400, 200, 100, 1);
        f.add(panelTavolo);
        creaPanelTavolo(100, 550, 200, 100, 4);
        f.add(panelTavolo);

        creaPanelTavolo(650, 400, 200, 100, 2);
        f.add(panelTavolo);
        creaPanelTavolo(650, 550, 200, 100, 5);
        f.add(panelTavolo);

        creaPanelTavolo(1200, 400, 200, 100, 3);
        f.add(panelTavolo);
        creaPanelTavolo(1200, 550, 200, 100, 6);
        f.add(panelTavolo);

        panelCucina.add(labelCucinaString);
        panelCameriere.add(labelCameriereString);
        panelClienti.add(labelClientiString);

        f.add(scrollPaneClientiOrdini);
        f.add(scrollPaneCucinaOrdini);
        f.add(scrollPaneCameriereOrdini);
        f.add(panelCucina);
        f.add(panelCameriere);
        f.add(panelClienti);

        f.setVisible(true);
    }

    public void creaPanelTavolo(int larghezzaPosizione, int altezzaPosizione, int larghezzaDimensione, int altezzaDimensione, int numeroTavolo) {
        panelTavolo = new JPanel();
        panelTavolo.setBackground(Color.ORANGE);
        panelTavolo.setLocation(larghezzaPosizione, altezzaPosizione);
        panelTavolo.setSize(larghezzaDimensione, altezzaDimensione);

        labelNumeroTavolo = new JLabel("Tavolo " + numeroTavolo);
        labelNumeroTavolo.setFont(new Font("Arial", Font.BOLD, 24));

        panelTavolo.add(labelNumeroTavolo);
    }

    @Override
    public void handleEvent(String message, EventType eventType) {
        SwingUtilities.invokeLater(() -> {
            JLabel label = new JLabel(message);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            switch (eventType) {
                case CLIENTE:
                    panelClientiOrdini.add(label);
                    break;
                case CAMERIERE:
                    panelCameriereOrdini.add(label);
                    break;
                case CUCINA:
                    panelCucinaOrdini.add(label);
                    break;
            }
            panelClientiOrdini.revalidate();
            panelClientiOrdini.repaint();
            panelCameriereOrdini.revalidate();
            panelCameriereOrdini.repaint();
            panelCucinaOrdini.revalidate();
            panelCucinaOrdini.repaint();
        });
    }
}
