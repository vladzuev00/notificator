package by.aurorasoft.notificator.configuration;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeometryFactoryConfiguration {
    private static final int GEOMETRY_FACTORY_SRID = 4326;
    private static final PrecisionModel GEOMETRY_FACTORY_PRECISION_MODEL = new PrecisionModel();

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory(GEOMETRY_FACTORY_PRECISION_MODEL, GEOMETRY_FACTORY_SRID);
    }
}
