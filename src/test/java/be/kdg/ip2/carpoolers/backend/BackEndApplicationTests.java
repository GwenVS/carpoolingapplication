package be.kdg.ip2.carpoolers.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
public class BackEndApplicationTests {

	@Test
	public void contextLoads() {
		boolean loads = true;
		assertThat(loads, is(true));
	}

}
