
package main_server;

import javax.swing.SwingUtilities;
import view.InicijalizacijaKonfiguracijaForma;
import view.KonfiguracijaBazaForma;
import view.KonfiguracijaExportForma;
import view.KonfiguracijaPortForma;
import view.ServerForma;

/**
 *
 * @author Andrej
 */
public class Main {

    public static void main(String[] args) {
        AppStyles.setLookAndFeel();
        SwingUtilities.invokeLater(() -> {
            ServerForma serverForma = new ServerForma();
                serverForma.kpf = new KonfiguracijaPortForma(serverForma, true);
                serverForma.kef = new KonfiguracijaExportForma(serverForma, true);
                serverForma.kbf = new KonfiguracijaBazaForma(serverForma, true);
                if (serverForma.isValidData()) {
                    serverForma.setVisible(true);
                } else {
                    InicijalizacijaKonfiguracijaForma iniForma = new InicijalizacijaKonfiguracijaForma(serverForma, true);
                    iniForma.setVisible(true);
                }
        });

    }

}
