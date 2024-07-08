package com.aurorasoft.notificator.config;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;

import static com.aurorasoft.notificator.config.GeometryFactoryConfig.SRID;
import static org.junit.jupiter.api.Assertions.*;

public final class GeometryFactoryConfigTest extends AbstractSpringBootTest {

    @Autowired
    private GeometryFactory geometryFactory;

    @Autowired
    private PrecisionModel precisionModel;

    @Test
    public void precisionModelShouldBeCreated() {
        PrecisionModel expected = new PrecisionModel();
        assertEquals(expected, precisionModel);
    }

    @Test
    public void geometryFactoryShouldBeCreated() {
        assertNotNull(geometryFactory);

        PrecisionModel actualPrecisionModel = geometryFactory.getPrecisionModel();
        assertSame(precisionModel, actualPrecisionModel);

        int actualSRID = geometryFactory.getSRID();
        assertEquals(SRID, actualSRID);
    }
}
