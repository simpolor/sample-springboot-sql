package io.simpolor.h2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.h2.H2Application;
import io.simpolor.h2.domain.Student;
import io.simpolor.h2.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {StudentController.class})
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStudentTotalcount() throws Exception {

        // given
        Long totalcount = 3L;
        when(studentService.getStudentTotalCount()).thenReturn(totalcount);


        // when
        MvcResult result = this.mockMvc.perform(get("/student/totalcount"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$")
                                .value(is(3))
                )
                .andReturn();

    }

    @Test
    public void testStudentList() throws Exception {

        // given
        Student student = new Student(1, "하니", 2, 18);
        when(studentService.getStudentList()).thenReturn(Arrays.asList(student));


        // when
        MvcResult result = this.mockMvc.perform(get("/student/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].name")
                                .value(is("하니"))
                )
                .andReturn();

    }

    @Test
    public void testStudentView() throws Exception {

        // given
        Student student = new Student(1, "하니", 2, 18);
        when(studentService.getStudent(anyLong())).thenReturn(student);


        // when
        MvcResult result = this.mockMvc.perform(get("/student/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.name")
                                .value(is("하니"))
                )
                .andReturn();

    }

    @Test
    public void testStudentSave() throws Exception {

        // given
        Student student = new Student(1, "하니", 2, 18);
        when(studentService.registerStudent(student)).thenReturn(student);

        String json = "{ \"seq\": 1, \"name\":\"하니\", \"grade\": 2, \"age\": 18 }";

        // when
        MvcResult result =
                this.mockMvc.perform(
                        post("/student")
                                .header("Accept", "application/json")
                        .contentType((MediaType.APPLICATION_JSON))
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.seq")
                                .value(is(1))
                )
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.name")
                                .value(is("하니"))
                )
                .andReturn();

    }

    @Test
    public void testStudentModofy() throws Exception {

        // given
        Student student = new Student(1, "하니", 2, 19);
        when(studentService.modifyStudent(student)).thenReturn(student);

        String json = "{ \"seq\": 1, \"name\":\"하니\", \"grade\": 2, \"age\": 19 }";

        // when
        MvcResult result =
                this.mockMvc.perform(
                        put("/student/1")
                                .header("Accept", "application/json")
                                .contentType((MediaType.APPLICATION_JSON))
                                .content(json)
                )
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk()).andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.seq")
                                .value(is(1))
                )
                        .andExpect(
                                MockMvcResultMatchers
                                        .jsonPath("$.age")
                                        .value(is(19))
                        )
                        .andReturn();

    }

    @Test
    public void testStudentDelete() throws Exception {

        // given
        Long seq = 1L;
        when(studentService.deleteStudent(seq)).thenReturn(seq);


        // when
        MvcResult result =
                this.mockMvc.perform(
                    delete("/student/1")
                )
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk()).andExpect(
                    MockMvcResultMatchers
                            .jsonPath("$")
                            .value(is(1))
                )
                .andReturn();

    }

}
