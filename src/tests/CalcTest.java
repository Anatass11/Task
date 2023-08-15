package tests;

import actions.Calc;
import actions.GUI;
import actions.LogDAO;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;

public class CalcTest {

    @Test
    public void actionPerformed() {
        try {
            File file = new File("src\\tests\\test2d.xml"); //пример xml для 2d
            GUI.setFile(file);
            Calc test = new Calc();
            test.actionPerformed(null);
            int max = LogDAO.max();
            assert(Math.sqrt(200) == LogDAO.findById(max).getDistance()); //тест 2d расчёта
            file = new File("src\\tests\\test3d.xml"); //пример xml для 3d
            GUI.setFile(file);
            test.actionPerformed(null);
            max = LogDAO.max();
            assert(Math.sqrt(209) == LogDAO.findById(max).getDistance());//тест 3d расчёта
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void clean() {
        for(int i = 0; i < 2; ++i) {
            LogDAO.delete(LogDAO.findById(LogDAO.max())); //удаление тестовых логов
        }
    }
}