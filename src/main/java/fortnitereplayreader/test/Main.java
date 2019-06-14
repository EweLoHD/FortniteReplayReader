package fortnitereplayreader.test;

import fortnitereplayreader.model.Elimination;
import fortnitereplayreader.FortniteReplayReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        File replayFile;
        FortniteReplayReader replay = null;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        jfc.setDialogTitle("Select a Replay");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fortnite Replay Files", "replay");
        jfc.addChoosableFileFilter(filter);
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            try {
                jfc.setCurrentDirectory(new File(System.getProperty("user.home") + "/appdata/Local/FortniteGame/Saved/Demos"));
            } catch (Exception e) {

            }
        }

        System.out.println("\nSelecting a File ...");

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            replayFile = jfc.getSelectedFile();
            try {
                replay = new FortniteReplayReader(replayFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(replay.getMeta().getFriendlyName());

        for (Elimination e : replay.getEliminations()) {
            System.out.println(e.getKillerId() + " killed " + e.getVictimId());
        }
    }

}
