package swing.vue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import code.Joueur;
import codenames.CodeNamesClient;
import swing.controleur.controleurLogin;
import swing.controleur.controleurRegister;
import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;






public class FenetreProfil extends JFrame{

    private JLabel mdp;
    private JLabel pseudo;
    private JLabel classement;
    private JLabel pWin;
    private JLabel pLoos;
    private JLabel pourcentageWin;
    private Image avatar;


    public FenetreProfil(String titre, Joueur joueur) {
        super(titre);


        JPanel main = new JPanel();
        this.setContentPane(main);
        ImageIcon icon = new ImageIcon("ressource/info-zoom.png"); //ICON
        this.setIconImage(icon.getImage());
        main.setLayout(new GridLayout(2,1));

        JPanel info = new JPanel();
        info.setBorder(BorderFactory
                .createTitledBorder("Informations du Joueur"));
        info.setLayout(new BorderLayout());


        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil"));
        profil.add(pseudo = new JLabel(joueur.getPseudo()));
        profil.add(mdp = new JLabel(joueur.getMdp()));

        Image img;
        Image imgSized = null;
        try {
            img = ImageIO.read(new File("ressource"+File.separator+"info4-min.png"));
            imgSized = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        } catch (IOException e) { e.printStackTrace(); }
        JLabel avatar = new JLabel(new ImageIcon(imgSized));

        info.add(avatar,BorderLayout.WEST);
        info.add(profil,BorderLayout.CENTER);

        main.add(info);

        //main.add(profil);


        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(1,2));

        JPanel winrate = new JPanel();
        winrate.setLayout(new BoxLayout(winrate, BoxLayout.Y_AXIS));
        winrate.setBorder(BorderFactory
                .createTitledBorder("Statistiques"));                    
        winrate.add(pWin = new JLabel("Partie gagné : " +joueur.getPWin()));
        winrate.add(pLoos = new JLabel("Partie perdue : " +joueur.getPLoos()));
        winrate.add(pourcentageWin = new JLabel("Winrate : " +joueur.getWinrate()));



        stats.add(winrate);



        JPanel classement = new JPanel();
        classement.setLayout(new BoxLayout(classement, BoxLayout.Y_AXIS));
        classement.setBorder(BorderFactory
                    .createTitledBorder("Classement"));
        classement.add(this.classement = new JLabel("Votre position dans le classement est : " + joueur.getClassement()));

        stats.add(classement);
        main.add(stats);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 - 250);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 - 150);
        this.setLocation(x, y);


        
    }


    

    
}