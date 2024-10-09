package com.aurorasoft.notificator.crud.entity;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.aurorasoft.notificator.testutil.GeofenceEntityUtil.assertEquals;

public final class GeofenceEntityTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    public void geofenceShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                    "geometry": {
                      "type": "Polygon",
                      "coordinates": [
                        [
                          [
                            1,
                            2
                          ],
                          [
                            1,
                            4
                          ],
                          [
                            3,
                            4
                          ],
                          [
                            3,
                            2
                          ],
                          [
                            1,
                            2
                          ]
                        ]
                      ]
                    },
                    "id": 255
                  }""";

        GeofenceEntity actual = objectMapper.readValue(givenJson, GeofenceEntity.class);
        GeofenceEntity expected = new GeofenceEntity(
                255L,
                geometryFactory.createPolygon(
                        new Coordinate[]{
                                new Coordinate(1, 2),
                                new Coordinate(1, 4),
                                new Coordinate(3, 4),
                                new Coordinate(3, 2),
                                new Coordinate(1, 2)
                        }
                )
        );
        assertEquals(expected, actual);
    }
}
