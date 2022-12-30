package dev.project.client.api.service;

import dev.project.client.api.dto.AbstractDto;

import java.util.List;

public interface ServiceContract<TDetailDto extends AbstractDto, TDto extends AbstractDto> {

    TDetailDto create(TDto dto);
    TDetailDto update(TDto dto);
    List<TDetailDto> findAll();
    TDetailDto findById(Long id);
}
