package by.aurorasoft.notificator.service;

import com.locator.server.repositories.LastCorrectTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@AllArgsConstructor
public class LastValidTimeService {

    private final LastCorrectTimeRepository repository;

    /**
     * key unitId
     */
    public Map<Long, Instant> map() {
        return repository.findUnitIdsToLastCorrectTimes();
    }
}
