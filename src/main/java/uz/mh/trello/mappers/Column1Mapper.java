package uz.mh.trello.mappers;

import org.mapstruct.Mapper;

import uz.mh.trello.domains.Column1;

import uz.mh.trello.dtos.ColumnCreateDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface Column1Mapper {
    List<ColumnCreateDto> toDto(List<Column1> boards);

    ColumnCreateDto toDto(Column1 board);

    Column1 fromCreateDto(ColumnCreateDto boardDto);
}
