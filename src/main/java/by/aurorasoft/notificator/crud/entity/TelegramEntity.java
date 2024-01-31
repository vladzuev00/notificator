package by.aurorasoft.notificator.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@ToString
@Entity
@Table(name = "telegram")
public class TelegramEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


}
