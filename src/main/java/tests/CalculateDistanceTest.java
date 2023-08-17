package tests;

import actions.CalculateDistance;
import actions.GUI;
import actions.LogDAO;
import org.junit.Test;
import org.junit.After;

import java.io.File;

public class CalculateDistanceTest {

    @Test
    public void actionPerformed() {
        try {
            File file = new File("src\\main\\java\\tests\\test2d.xml"); //пример xml для 2d
            GUI testg = new GUI();
            testg.setFile(file);
            CalculateDistance test = new CalculateDistance(testg);
            test.actionPerformed(null);
            int max = LogDAO.max();
            assert(Math.sqrt(200) == LogDAO.findById(max).getDistance()); //тест 2d расчёта
            file = new File("src\\main\\java\\tests\\test3d.xml"); //пример xml для 3d
            testg.setFile(file);
            test.actionPerformed(null);
            max = LogDAO.max();
            assert(Math.sqrt(209) == LogDAO.findById(max).getDistance());//тест 3d расчёта
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}