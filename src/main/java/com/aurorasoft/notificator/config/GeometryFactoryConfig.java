package com.aurorasoft.notificator.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeometryFactoryConfig {
    static final int SRID = 4326;

    @Bean
    public PrecisionModel precisionModel() {
        return new PrecisionModel();
    }

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory(precisionModel(), SRID);
    }
}
