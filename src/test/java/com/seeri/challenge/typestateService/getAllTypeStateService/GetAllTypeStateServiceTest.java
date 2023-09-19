package com.seeri.challenge.typestateService.getAllTypeStateService;

import com.seeri.challenge.entities.TypeStateEntity;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.repositorys.TypeStateRepository;
import com.seeri.challenge.services.typestateService.getAllTypeStateService.GetAllTypeStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class GetAllTypeStateServiceTest {
    @InjectMocks
    private GetAllTypeStateService getAllTypeStateService;
    @Mock
    private TypeStateRepository typeStateRepositoryImpl;

    @BeforeEach
    void setUp() {
        List<TypeStateEntity> listTypeState = new ArrayList<>();
        Date date = new Date();
        TypeStateEntity typeStateEntity = new TypeStateEntity();
        typeStateEntity.setState(true);
        typeStateEntity.setName("Prueba");
        typeStateEntity.setTypeStateId(1);
        typeStateEntity.setCreateDate(date);
        listTypeState.add(typeStateEntity);
        when(typeStateRepositoryImpl.findAll()).thenReturn(listTypeState);
    }

    @Test
    void getAllTypeStateTrue() {
        ResponseControllerModel result = getAllTypeStateService.getAllTypeState();
        assertEquals(200, result.getStatus());
        assertNotNull(result.getResponse());
        assertEquals(false, result.getResponse().isWarning());
        assertEquals(true, result.getResponse().isSuccess());
    }
    @Test
    void getAllTypeStateFalse() {
        when(typeStateRepositoryImpl.findAll()).thenReturn(null);
        ResponseControllerModel result = getAllTypeStateService.getAllTypeState();
        assertEquals(404, result.getStatus());
        assertNotNull(result.getResponse());
        assertEquals(true, result.getResponse().isWarning());
        assertEquals(false, result.getResponse().isSuccess());
    }
}