package com.gigsync.syncrm.models;
package example.employee;


import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
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

        @Autowired
        private JacksonTester<Employee[]> jsonList;

        private Employee[] employee;

        @BeforeEach
        void setUp() {
            employee = Arrays.array(
                    new Employee(99L, "John"),
                    new Employee(100L, "James"),
                    new Employee(101L, "Sabrina"));
        }

        @Test
        void employeeSerializationTest() throws IOException {
            Employee employee = employee[0];
            assertThat(json.write(employee)).isStrictlyEqualToJson("single.json");
            assertThat(json.write(employee)).hasJsonPathNumberValue("@.id");
            assertThat(json.write(employee)).extractingJsonPathNumberValue("@.id")
                    .isEqualTo(99);
            assertThat(json.write(employee)).hasJsonPathNumberValue("@.amount");
            assertThat(json.write(employee)).extractingJsonPathNumberValue("@.amount")
                    .isEqualTo(123.45);
        }

        @Test
        void employeeDeserializationTest() throws IOException {
            String expected = """
                {
                    "id": 99,
                    "name": John
                }
                """;
            assertThat(json.parse(expected))
                    .isEqualTo(new Employee(99L, 123.45));
            assertThat(json.parseObject(expected).id()).isEqualTo(99);
            assertThat(json.parseObject(expected).name()).isEqualTo("john");
        }

        @Test
        void employeeListSerializationTest() throws IOException {
            assertThat(jsonList.write(employee)).isStrictlyEqualToJson("list.json");
        }

        @Test
        void employeeListDeserializationTest() throws IOException {
            String expected="""
             [
                { "id": 99, "name": John },
                { "id": 100, "name": James },
                { "id": 101, "name": Sabrina }
             ]
             """;
            assertThat(jsonList.parse(expected)).isEqualTo(employee);
        }
}
