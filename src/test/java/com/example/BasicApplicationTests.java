package com.example;

import com.example.repositories.ProcessorRepository;
import java.util.ArrayList;
import java.util.List;
import static org.graalvm.compiler.graph.iterators.NodePredicates.isA;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.Lifecycle;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasicApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void limpar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/reset"))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/listMovimentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(0)));
    }

    @Test
    public void testAUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "CNAB.txt", MediaType.TEXT_PLAIN_VALUE, getClass().getResourceAsStream("/CNAB.txt"));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/processFile").file(file))
                .andExpect(status().isOk());
    }

    @Test
    public void testBList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/listMovimentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[*].nomeLoja", containsInAnyOrder("MERCADO DA AVENIDA", "BAR DO JOÃO", "LOJA DO Ó - MATRIZ")))
                .andDo(MockMvcResultHandlers.print());
    }
}
