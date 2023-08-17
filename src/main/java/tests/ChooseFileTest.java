package tests;

import actions.ChooseFile;
import actions.GUI;
import org.junit.Test;

import java.io.File;

public class ChooseFileTest {

    @Test
    public void actionPerformed() {
        try {
            File file = new File("src\\tests\\test2d.xml"); //тест для проверки сохранения файла
            GUI testg = new GUI();
            testg.setFile(file);
            ChooseFile test = new ChooseFile(testg);
            test.actionPerformed(null);
            assert (file.getName().equals(testg.getFile().getName()));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}