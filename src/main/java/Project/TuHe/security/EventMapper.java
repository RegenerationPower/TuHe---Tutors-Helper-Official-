package Project.TuHe.security;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventEntity toEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, EventEntity.class);
    }

    public EventDTO toDTO(EventEntity eventEntity) {
        return modelMapper.map(eventEntity, EventDTO.class);
    }
}
