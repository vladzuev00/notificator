package com.aurorasoft.notificator.config;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class ObjectMapperConfigTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void objectMapperShouldBeCreated() {
        assertNotNull(objectMapper);

        Set<Object> actual = objectMapper.getRegisteredModuleIds();
        Set<Object> expected = Set.of("jackson-datatype-jts", "json-view");
        assertEquals(expected, actual);
    }
}
