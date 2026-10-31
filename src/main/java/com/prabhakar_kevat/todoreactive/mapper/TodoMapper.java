package com.prabhakar_kevat.todoreactive.mapper;

import org.mapstruct.Mapper;

import com.prabhakar_kevat.todoreactive.entity.TodoEntity;
import com.prabhakar_kevat.todoreactive.model.TodoDTO;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDTO entityToDto(TodoEntity entity);
    TodoEntity dtoToEntity(TodoDTO dto);
}
