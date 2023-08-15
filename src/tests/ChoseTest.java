package tests;

import actions.Chose;
import actions.GUI;
import org.junit.Test;

import java.io.File;

public class ChoseTest {

    @Test
    public void actionPerformed() {
        try {
            File file = new File("src\\tests\\test2d.xml"); //тест для проверки сохранения файла
            Chose test = new Chose();
            test.actionPerformed(null);
            assert (file.getName().equals(GUI.getFile().getName()));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}