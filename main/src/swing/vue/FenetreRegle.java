package swing.vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FenetreRegle extends JFrame {

    private Image img;
    private JButton retour;

    public FenetreRegle(String name) {
        super(name);

        JPanel main = new JPanel(new BorderLayout());
        this.setContentPane(main);

        JPanel reglePan = new JPanel();
        reglePan.setLayout(new BoxLayout(reglePan, BoxLayout.Y_AXIS));
        try {
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle1.png"));
            JLabel lab1 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle2.png"));
            JLabel lab2 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle3.png"));
            JLabel lab3 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle4.png"));
            JLabel lab4 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle5.png"));
            JLabel lab5 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle6.png"));
            JLabel lab6 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle7.png"));
            JLabel lab7 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle8.png"));
            JLabel lab8 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle9.png"));
            JLabel lab9 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle10.png"));
            JLabel lab10 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle11.png"));
            JLabel lab11 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle12.png"));
            JLabel lab12 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle13.png"));
            JLabel lab13 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle14.png"));
            JLabel lab14 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle15.png"));
            JLabel lab15 = new JLabel(new ImageIcon(img));
            img = ImageIO.read(new File("ressource"+File.separator+"regle"+File.separator+"regle16.png"));
            JLabel lab16 = new JLabel(new ImageIcon(img));

            reglePan.add(lab1);
            reglePan.add(lab2);
            reglePan.add(lab3);
            reglePan.add(lab4);
            reglePan.add(lab5);
            reglePan.add(lab6);
            reglePan.add(lab7);
            reglePan.add(lab8);
            reglePan.add(lab9);
            reglePan.add(lab10);
            reglePan.add(lab11);
            reglePan.add(lab12);
            reglePan.add(lab13);
            reglePan.add(lab14);
            reglePan.add(lab15);
            reglePan.add(lab16);
        } catch (IOException e) { e.printStackTrace(); }

        JScrollPane scroll = new JScrollPane(reglePan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JPanel retourPan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        retourPan.add(retour = new JButton("retour"));

        main.add(retourPan, BorderLayout.SOUTH);
        main.add(scroll, BorderLayout.CENTER);

        // listener
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fermer();
            }
        });

        // vue
        this.pack();

        //this.setSize(new Dimension(500, 800));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = 0;
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void fermer() {
        dispose();
    }
}
