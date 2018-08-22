package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.domain.Car;
import be.kdg.ip2.carpoolingapplication.domain.enums.Gender;
import be.kdg.ip2.carpoolingapplication.domain.user.Authority;
import be.kdg.ip2.carpoolingapplication.domain.user.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class CarControllerTest {
    static Car car;
    static User user;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        user = new User("John", "Doe", "john", "carpooler_M_S_1@Doe.com", LocalDate.of(1997,3,5), "testtest", Gender.Male, new ArrayList<>());
        car = new Car("JSONCar",6.2,3,user);
        car.setCarId((long)1);
    }
    @Test
    public void TestCreateCar() throws Exception {
        JSONObject jsonObject = new JSONObject("{\"carId\":1,\"type\":\"JSONCar\",\"consumption\":6.2, \"maxAmountPassengers\":3}");
        MvcResult mvcResult = mockMvc.perform(post("api/public/cars/user/" + user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
                .andReturn();
        Assert.assertNotNull(mvcResult);
        //Assert.assertThat(mvcResult.getResponse().getStatus(), equalTo(HttpStatus.OK));
        //Assert.assertThat(mvcResult.getResponse().getContentAsString().getClass(), equalTo(Car.class));
    }
}
