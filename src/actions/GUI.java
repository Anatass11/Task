package actions;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUI extends JFrame {
    private JButton button = new JButton("Запустить расчёт");
    private JButton chose = new JButton("Выбрать файл");
    private JButton make = new JButton("Создать таблицу");
    private static JLabel text = new JLabel("Файл не выбран!");
    public static void setText(String mes){
        text.setText(mes);
        text.validate();
    }
    private static JTable info; //для вывода результатов расчёта
    public static void setInfo(JTable N){
        if(container == null) return;
        if(info != null) container.remove(info);
        info = N;
        JPanel lay = new JPanel();
        lay.setLayout(new BoxLayout(lay, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(info);
        lay.add(new JLabel("Результаты расчётов: "));
        lay.add(scrollPane);
        container.add(lay, BorderLayout.WEST);
        container.validate();
    }
    private JButton logs = new JButton("Вывести логи");
    private static JTable log; //для вывода логов с базы данных
    public static void setLog(JTable N){
        if(container == null) return;
        if(log != null) container.remove(log);
        log = N;
        JPanel lay = new JPanel();
        lay.setLayout(new BoxLayout(lay, BoxLayout.Y_AXIS));
        lay.add(new JLabel("Логи базы данных: "));
        JScrollPane scrollPane = new JScrollPane(log);
        lay.add(scrollPane);
        container.add(lay,  BorderLayout.CENTER);
        container.validate();
    }
    private static File chosen; //выбранный файл для считывания
    public static void setFile(File file){
        chosen = file;
    }
    public static File getFile(){
        return chosen;
    }

    private static Container container;

    public GUI() {
        super("Task");
        this.setBounds(10, 10, 1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel layoutn = new JPanel();
        layoutn.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel layouts = new JPanel();
        layouts.setLayout(new FlowLayout(FlowLayout.CENTER));

        container = this.getContentPane();
        button.addActionListener(new Calc()); //начать расчёт
        chose.addActionListener(new Chose()); //выбрать файл
        make.addActionListener(new Table()); //создать excel таблицу
        logs.addActionListener(new Logs()); //вывести логи бд
        layoutn.add(chose, BorderLayout.NORTH);
        layoutn.add(text, BorderLayout.NORTH);
        layoutn.add(button, BorderLayout.NORTH);
        container.add(layoutn, BorderLayout.NORTH);
        layouts.add(logs, "South");
        layouts.add(make, "South");
        container.add(layouts, "South");
    }
}
