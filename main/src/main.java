import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;

public class main {
    public static void main(String[] args) throws CnNetworkException {
        CodeNamesClient CodenameClient = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
        System.out.println(CodenameClient.allGames());
    }
}

