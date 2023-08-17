package actions;

import Entity.LogEntity;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static actions.ChooseFile.getFileExtension;

public class CreateTable implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle style1 = workbook.createCellStyle();
        HSSFDataFormat form = workbook.createDataFormat();
        style.setDataFormat(form.getFormat("dd/mm/yy hh:mm:ss")); //стиль для дат
        style1.setAlignment(HorizontalAlignment.RIGHT); //стиль для точек
        // создание листа с названием
        HSSFSheet sheet = workbook.createSheet("Логи");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("PAIR_ID");
        row.createCell(2).setCellValue("START");
        row.createCell(3).setCellValue("END");
        row.createCell(4).setCellValue("POINT_A");
        row.createCell(5).setCellValue("POINT_B");
        row.createCell(6).setCellValue("DISTANCE");
        try {
            List<LogEntity> list = LogDAO.findAll();
            for(int i = 0; i < list.size(); ++i){ //считываем и записываем данные из бд в строку, применяем стили
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(list.get(i).getId());
                row.createCell(1).setCellValue(list.get(i).getPairId());
                row.createCell(2).setCellValue(new Date(list.get(i).getStart().getTime()));
                row.getCell(2).setCellStyle(style);
                row.createCell(3).setCellValue(new Date(list.get(i).getEnd().getTime()));
                row.getCell(3).setCellStyle(style);
                row.createCell(4).setCellValue(list.get(i).getPointA());
                row.getCell(4).setCellStyle(style1);
                row.createCell(5).setCellValue(list.get(i).getPointB());
                row.getCell(5).setCellStyle(style1);
                row.createCell(6).setCellValue(list.get(i).getDistance());
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Сохранение файла");
            // Определение режима - только файл
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Excel", "xlsx", "xls");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showSaveDialog(null); //выбираем место для сохранения
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if(!getFileExtension(file).equals("xls") || !getFileExtension(file).equals("xlsx")){ //преобразование файла в xls
                    String path = file.getPath();
                    if (path.lastIndexOf(".") != -1 && path.lastIndexOf(".") != 0) {
                        String new_path = path.substring(0, path.lastIndexOf("."));
                        file = new File(new_path + ".xls");
                    }else {
                        file = new File(path + ".xls");
                    }
                }
                FileOutputStream out = new FileOutputStream(file); //настройка excel таблицы
                sheet.setColumnWidth(0, 2000);
                sheet.setColumnWidth(1, 2000);
                sheet.setColumnWidth(2, 10000);
                sheet.setColumnWidth(3, 10000);
                sheet.setColumnWidth(4, 10000);
                sheet.setColumnWidth(5, 10000);
                sheet.setColumnWidth(6, 10000);
                workbook.write(out); //запись таблицы в созданный файл
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
