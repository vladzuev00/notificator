package by.aurorasoft.notificator.configuration;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JtsGeometryConfiguration {
    private static final PrecisionModel PRECISION_MODEL = new PrecisionModel();
    private static final int SRID = 4326;

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory(PRECISION_MODEL, SRID);
    }
}
