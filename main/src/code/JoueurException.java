/**
 * @author Les Infopotes
 * @version 2.13
 */

package code;

/**
 * Classe exception levée en cas d'erreur dans les candidatures
 */
public class JoueurException extends Exception {
    /**
     * créé une nouvelle exception
     *
     * @param message le message associé à l'exception
     */
    public JoueurException(String message) {
            super(message);
        }
}
