package actions;

import Entity.LogEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Logs implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columNames = {"ID", "Pair_id", "Start", "End", "Point A", "Point B", "Distance"};
        int max = LogDAO.max();
        Object[][] data = new Object[max][];
        List<LogEntity> list = LogDAO.findAll();
        for (int i = 0; i < list.size(); ++i) { //берём все логи и записываем в таблицу
            data[i] = list.get(i).getLog();
        }
        JTable log = new JTable(data, columNames) { //создаём и настраиваем таблицу
            @Override
            public boolean isCellEditable(int row, int cell) {
                return false;
            }
        };
        log.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        log.getColumnModel().getColumn(0).setPreferredWidth(20);
        log.getColumnModel().getColumn(1).setPreferredWidth(50);
        log.getColumnModel().getColumn(2).setPreferredWidth(125);
        log.getColumnModel().getColumn(3).setPreferredWidth(125);
        log.getColumnModel().getColumn(4).setPreferredWidth(125);
        log.getColumnModel().getColumn(5).setPreferredWidth(125);
        log.getColumnModel().getColumn(6).setPreferredWidth(125);
        GUI.setLog(log); //выводим таблицу
    }
}
