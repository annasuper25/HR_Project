package ru.atc.hrsample.service.api;


import ru.atc.hrsample.dto.JobsDto;

import java.util.List;

/**
 * Сервис для работы с работами
 */
public interface JobsService {

    /**
     * Получает сущность работы по ид
     *
     * @param id идентификатор
     * @return сохраненная сущность с ид.
     */
    JobsDto getJobById(String id);

    /**
     * Сохраняет сущность работы
     *
     * @param dto сущность работы
     * @return сохраненная сущность с ид.
     */
    JobsDto insertJob(JobsDto dto);


    /**
     * Получает все работы
     * @return список всех работ
     */
    List<JobsDto> getAll();
}
