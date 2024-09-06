package com.gigsync.syncrmAPI;


import com.gigsync.syncrmAPI.Models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class EmployeeJsonTest {

    @Autowired
    private JacksonTester<Employee> json;

    @Test
    void employeeSerializationTest() throws IOException {
        Employee employee = new Employee(99L, "Eyedress");
        assertThat(json.write(employee)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(employee)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(employee)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(employee)).hasJsonPathStringValue("@.name");
        assertThat(json.write(employee)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Eyedress");
    }

}
