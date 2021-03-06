package io.simpolor.jdbc.controller;

import io.simpolor.jdbc.model.StudentDto;
import io.simpolor.jdbc.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
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
    public void testTotalCount() throws Exception {

        // given
        Long totalCount = 3L;
        when(studentService.getTotalCount()).thenReturn(totalCount);

        // when
        this.mockMvc.perform(
                get("/students/total-count")
        )
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
    public void testList() throws Exception {

        // given
        StudentDto student = new StudentDto(1L, "??????", 2, 18, Arrays.asList("??????"));
        when(studentService.getAll()).thenReturn(Arrays.asList(student));

        // when, then
        this.mockMvc.perform(
                get("/students")
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(
                MockMvcResultMatchers
                        .jsonPath("$[0].name")
                        .value(is("??????"))
        )
        .andReturn();

    }

    @Test
    public void testDetail() throws Exception {

        // given
        StudentDto student = new StudentDto(1, "??????", 2, 18, Arrays.asList("??????"));
        when(studentService.get(anyLong())).thenReturn(student);

        // when, then
        this.mockMvc.perform(
                get("/students/1")
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(
                MockMvcResultMatchers
                        .jsonPath("$.name")
                        .value(is("??????"))
        )
        .andReturn();
    }

    @Test
    public void testRegister() throws Exception {

        // given
        String json = "{ \"seq\": 1, \"name\":\"??????\", \"grade\": 2, \"age\": 18 }";

        // when, then
        this.mockMvc.perform(
                post("/students")
                    .header("Accept", "application/json")
                    .contentType((MediaType.APPLICATION_JSON))
                    .content(json)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andReturn();
    }

    @Test
    public void testModify() throws Exception {

        // given
        String json = "{ \"seq\": 1, \"name\":\"??????\", \"grade\": 2, \"age\": 19 }";

        // when, then
        this.mockMvc.perform(
                put("/students/1")
                    .header("Accept", "application/json")
                    .contentType((MediaType.APPLICATION_JSON))
                    .content(json)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andReturn();
    }

    @Test
    public void testDelete() throws Exception {

        // given
        long seq = 1L;

        // when, then
        this.mockMvc.perform(
            delete("/students/{seq}", seq)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andReturn();
    }

}
