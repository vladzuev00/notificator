package com.aurorasoft.notificator.crud.service;

import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import com.aurorasoft.notificator.crud.dto.TimeZone;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import com.aurorasoft.notificator.crud.mapper.TimeZoneMapper;
import com.aurorasoft.notificator.crud.repository.TimeZoneRepository;
import org.springframework.stereotype.Service;

@Service
public class TimeZoneService extends AbsServiceCRUD<Long, TimeZoneEntity, TimeZone, TimeZoneRepository> {

    public TimeZoneService(final TimeZoneMapper mapper, final TimeZoneRepository repository) {
        super(mapper, repository);
    }
}
