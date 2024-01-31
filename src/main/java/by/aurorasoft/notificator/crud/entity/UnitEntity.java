package by.aurorasoft.notificator.crud.entity;

import by.aurorasoft.notificator.model.UnitStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.type.descriptor.converter.spi.EnumValueConverter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@ToString
@Entity
@Table(name = "unit")
public class UnitEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Convert(converter = EnumValueConverter.class)
    private UnitStatus status;
}
