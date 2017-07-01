import com.hong.Application;
import com.hong.domain.Student;
import com.hong.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hong on 2017/5/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class EHCacheTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private StudentService studentService;

    @Before
    public void setup() {
//        Student student = new Student();
//        student.setName("张三");
//        student.setAge(11);
//        student.setAddress("湖南常德");
//        studentService.save(student);

    }

    @Test
    public void test() {
        Student student = studentService.findById(1L);
        logger.info("学生信息----:"+student.toString());
        Student student2 = studentService.findById(1L);
        logger.info("学生信息2----:"+student2.toString());

    }
}
