package by.aurorasoft.notificator.bymessage.vo;

import by.nhorushko.crudgeneric.domain.AbstractDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZoneId;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Unit implements AbstractDto {

    private final Long id;
    private final String name;
    private final ZoneId ownerTZ;

    @JsonCreator
    public Unit(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("ownerTZ") ZoneId ownerTZ) {
        this.id = id;
        this.name = name;
        this.ownerTZ = ownerTZ;
    }
}

