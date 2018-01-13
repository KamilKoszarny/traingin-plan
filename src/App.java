import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import sun.misc.Unsafe;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class App {

    App(){
        SkillsManager skillsManager = new SkillsManager();
        skillsManager.showSkills();
    }

    public static void main(String[] args) {
        disableWarning();

        App app = new App();

        try {
            Workbook wb = WorkbookFactory.create(new File("../Plan.xlsx"));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    //Hide warning “Illegal reflective access” in java 9
    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }

}
