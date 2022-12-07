package dev.project.client.api.service;

import dev.project.client.api.dto.AbstractDto;

import java.util.List;

public interface ServiceContract<TDetailDto extends AbstractDto, TDto extends AbstractDto> {

    public TDetailDto create(TDto dto);
    public TDetailDto update(TDto dto);
    public List<TDetailDto> findAll();
    public TDetailDto findById(Long id);

}
