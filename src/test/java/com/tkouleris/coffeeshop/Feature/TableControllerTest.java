package com.tkouleris.coffeeshop.Feature;

import com.google.gson.Gson;
import com.tkouleris.coffeeshop.model.Tables;
import com.tkouleris.coffeeshop.repository.TablesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TablesRepository tablesRepository;

    @Test
    public void creatingNewTable() throws Exception {
        Tables request = new Tables();
        request.setTable_code("TA01");
        request.setActive(true);
        Gson gson = new Gson();
        String json = gson.toJson(request);

        Mockito.when(tablesRepository.save(request)).thenReturn(request);

        MvcResult response = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/tables/create")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andReturn();
        Assertions.assertEquals(200,response.getResponse().getStatus());
    }
}
