package ru.atc.hrsample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.atc.hrsample.dao.JobsMapper;
import ru.atc.hrsample.dto.JobsDto;
import ru.atc.hrsample.dto.LocationsDto;
import ru.atc.hrsample.entity.JobsEntity;
import ru.atc.hrsample.entity.LocationsEntity;
import ru.atc.hrsample.service.api.JobsService;

import java.util.List;

/**
 * Реализация сервиса по работе с работами
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JobsServiceImpl implements JobsService {

    private final ModelMapper modelMapper;
    private final JobsMapper jobsMapper;

    @Override
    @Transactional(readOnly = true)
    public JobsDto getJobById(String id) {
        JobsEntity jobsEntity = jobsMapper.getJobById(id).orElseThrow(
                () -> new RuntimeException(
                        String.format("Не найдена сущность работы по идентификатору=%s", id)
                )
        );
        log.debug("Получена сущность работы с id={}", id);
        return modelMapper.map(jobsEntity, JobsDto.class);
    }

    @Override
    @Transactional
    public JobsDto insertJob(JobsDto dto) {
        JobsEntity jobsEntity = modelMapper.map(dto, JobsEntity.class);
        jobsMapper.insert(jobsEntity);
        log.info("Создана сущность с id={}", jobsEntity.getJobId());
        return getJobById(jobsEntity.getJobId());
    }


    @Override
    @Transactional(readOnly = true)
    public List<JobsDto> getAll() {
        List<JobsEntity> jobsEntities = jobsMapper.getAll();
        log.debug("Получены все сущности работ");
        return modelMapper.map(jobsEntities, TypeToken.of(List.class).getType());
    }
}
