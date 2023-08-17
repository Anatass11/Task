package actions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseFile implements ActionListener {
    private GUI gui;
    public ChooseFile(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Xml", "xml");
        fileopen.setFileFilter(filter);
        int ret = fileopen.showDialog(null, "Открыть файл"); //окно выбора файла с фильтром на xml
        if (ret == JFileChooser.APPROVE_OPTION) {
            gui.setFile(fileopen.getSelectedFile());
            if (!getFileExtension(gui.getFile()).equals("xml")) {
                JOptionPane.showMessageDialog(null, "Только xml!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                gui.setFile(null);
            }else {
                gui.setText(gui.getFile().getName()); //сохраняем файл
            }
        }
    }

    static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

}
