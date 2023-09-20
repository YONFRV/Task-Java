package com.seeri.challenge.controller;

import com.seeri.challenge.model.ResponseControllerModel;
import com.seeri.challenge.model.ResponseModel;
import com.seeri.challenge.service.typestateService.getAllTypeStateService.GetAllTypeStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig
@AutoConfigureMockMvc
class TypeStateControllerTest {

    @Autowired
    private MockMvc mockMvc; // Inyecta un MockMvc para simular solicitudes HTTP
    @MockBean
    private GetAllTypeStateService getAllTypeStateService; // Inyecta el servicio real o un mock

    @BeforeEach
    void setUp() {
        ResponseControllerModel respose = new ResponseControllerModel();
        ResponseModel<String> responseResult= new ResponseModel<>();
        responseResult.setMessage("Sin datos");
        responseResult.setWarning(true);
        responseResult.setSuccess(false);
        respose.setStatus(404);
        respose.setResponse(responseResult);
        when(getAllTypeStateService.getAllTypeState()).thenReturn(respose);
    }

    @Test
    public void testFullTypeStatesFalse() throws Exception {
        // Realiza la prueba
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/full-types-states")
                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Sin datos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.warning").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
    }
}