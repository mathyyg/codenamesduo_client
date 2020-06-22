package code;

public class indice {
    private String indice;
    private int nbMotPourIndice;

    public indice(String s, int nb){
        indice = s;
        nbMotPourIndice = nb;
    }

    public String getIndice() { return indice; }
    public int getNbMotPourIndice() { return nbMotPourIndice; }

    public void MotTrouve() {
        if (nbMotPourIndice > 0)
            nbMotPourIndice--;
    }

    public boolean estValide() {
        return false;
    }
}
