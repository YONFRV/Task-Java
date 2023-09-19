package com.seeri.challenge.typestateService.getByTypeStateService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seeri.challenge.entitys.TypeStateEntity;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.repositorys.TypeStateRepository;
import com.seeri.challenge.services.typestateService.getByTypeStateService.GetByTypeStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class GetByTypeStateServiceTest {

    @InjectMocks
    private GetByTypeStateService getByTypeStateService;
    @Mock
    private TypeStateRepository typeStateRepositoryImpl;
    private Gson gson = new Gson();


    @BeforeEach
    void setUp() {
        TypeStateEntity typeStateEntity = new TypeStateEntity();
        typeStateEntity.setState(true);
        typeStateEntity.setName("Prueba");
        typeStateEntity.setTypeStateId(1l);
        when(typeStateRepositoryImpl.findById(typeStateEntity.getTypeStateId())).thenReturn(Optional.of(typeStateEntity));
    }

    @Test
    void getByIdTypeStateTrue() {
        ResponseControllerModel result = getByTypeStateService.getByIdTypeState(1l);
        TypeStateEntity typeState = gson.fromJson(result.getResponse().getData(), TypeStateEntity.class);
        assertEquals(200, result.getStatus());
        assertEquals(1, typeState.getTypeStateId());
        assertEquals("Prueba", typeState.getName());
    }

    @Test
    void getByIdTypeStateFalse() {
        ResponseControllerModel result = getByTypeStateService.getByIdTypeState(2l);
        assertEquals(404, result.getStatus());
        assertEquals(false, result.getResponse().isSuccess());
        assertEquals(true, result.getResponse().isWarning());
    }
}