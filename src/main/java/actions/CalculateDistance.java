package actions;

import Entity.LogEntity;
import calculate.Calculate2D;
import calculate.Calculate3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import points.Point2D;
import points.Point3D;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;

public class CalculateDistance implements ActionListener {
    private GUI gui;
    public CalculateDistance(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed (ActionEvent e){
        try {
            if(gui.getFile() != null) {
                String[] columNames = {"Point A", "Point B", "Distance"};
                boolean d3 = false;
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                builder = factory.newDocumentBuilder();
                Document doc = builder.parse(gui.getFile()); //считывание выбранного файла
                doc.getDocumentElement().normalize();
                // получаем узлы
                NodeList nodeList = doc.getElementsByTagName("pair2d");
                if(nodeList.getLength() <= 0){
                    nodeList = doc.getElementsByTagName("pair3d");
                    d3 = true;
                }
                Object[][] data = new Object[nodeList.getLength()][];
                for (int temp = 0; temp < nodeList.getLength(); temp++) { //проводим расчёты и заполняем таблицу
                    Node nNode = nodeList.item(temp);
                    Element eElement = (Element) nNode;
                    NodeList nodes = eElement.getElementsByTagName("A"); //получаем первую точку
                    Node nA = nodes.item(0);
                    Element eA = (Element) nA;
                    nodes = eElement.getElementsByTagName("B"); //получаем вторую точку
                    Node nB = nodes.item(0);
                    Element eB = (Element) nB;
                    LogEntity log = new LogEntity(); //объект логов для связи с бд
                    log.setId(LogDAO.max() + 1);
                    log.setPairId(temp + 1);
                    double res = 0;
                    String sA, sB;
                    if (d3) { //если 3D
                        //создаём объекты точек
                        Point3D A = new Point3D(Double.parseDouble(eA.getElementsByTagName("x").item(0).getTextContent()),
                                Double.parseDouble(eA.getElementsByTagName("y").item(0).getTextContent()),
                                Double.parseDouble(eA.getElementsByTagName("z").item(0).getTextContent()));
                        Point3D B = new Point3D(Double.parseDouble(eB.getElementsByTagName("x").item(0).getTextContent()),
                                Double.parseDouble(eB.getElementsByTagName("y").item(0).getTextContent()),
                                Double.parseDouble(eB.getElementsByTagName("z").item(0).getTextContent()));
                        sA = A.toString(); //для таблицы
                        sB = B.toString();
                        log.setStart(new Timestamp(System.currentTimeMillis()));
                        res = Calculate3D.calculate(A, B); //расчёт 3D
                        log.setEnd(new Timestamp(System.currentTimeMillis()));

                    } else { //2D
                        //создаём объекты точек
                        Point2D A = new Point2D(Double.parseDouble(eA.getElementsByTagName("x").item(0).getTextContent()),
                                Double.parseDouble(eA.getElementsByTagName("y").item(0).getTextContent()));
                        Point2D B = new Point2D(Double.parseDouble(eB.getElementsByTagName("x").item(0).getTextContent()),
                                Double.parseDouble(eB.getElementsByTagName("y").item(0).getTextContent()));
                        sA = A.toString(); //для таблицы
                        sB = B.toString();
                        log.setStart(new Timestamp(System.currentTimeMillis()));
                        res = Calculate2D.calculate(A, B); //расчёт
                        log.setEnd(new Timestamp(System.currentTimeMillis()));
                    }
                    log.setPointA(sA);
                    log.setPointB(sB);
                    log.setDistance(res);
                    LogDAO.saveLog(log); //записываем лог в бд
                    data[temp] = new Object[]{sA, sB, res}; //создаём строку данных для таблицы
                }
                JTable info = new JTable(data, columNames) { //создаём таблицу, в которой нельзя менять данные
                    @Override
                    public boolean isCellEditable(int row, int cell) {
                        return false;
                    }
                };
                info.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //настройка размеров столбцов
                info.getColumnModel().getColumn(0).setPreferredWidth(100);
                info.getColumnModel().getColumn(1).setPreferredWidth(100);
                info.getColumnModel().getColumn(2).setPreferredWidth(150);
                gui.setInfo(info); //вывод таблицы

            }
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            throw new RuntimeException(ex);
        }

    }
}
