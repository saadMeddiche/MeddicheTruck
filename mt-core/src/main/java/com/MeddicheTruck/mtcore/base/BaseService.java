package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends BaseEntity, DTO extends BaseEntityDto, R extends BaseRepository<E> > implements BaseServiceInterface<E,DTO> {

    abstract protected String recordName();

    protected R repository;

    protected Class<E> entityClass;

    protected Class<DTO> dtoClass;

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    protected BaseService(R repository , Class<E> entityClass , Class<DTO> dtoClass){
        this.repository = repository;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public List<DTO> saveAll(List<DTO> entities){
        List<E> entitiesToSave = entities.stream().map(dto -> mapper.map(dto, entityClass)).toList();
        List<E> savedEntities = repository.saveAll(entitiesToSave);
        return savedEntities.stream().map(entity -> mapper.map(entity, dtoClass)).toList();
    }
    public DTO save(DTO entity){
        E entityToSave = mapper.map(entity, entityClass);
        E  savedEntity = (E) repository.save(entityToSave);
        return mapper.map(savedEntity, dtoClass);
    }

    public DTO update(DTO entity){
        E entityToUpdate = mapper.map(entity, entityClass);
        E updatedEntity = repository.save(entityToUpdate);
        return mapper.map(updatedEntity, dtoClass);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public DTO findById(Long id){
        Optional<E> optionalEntity = repository.findById(id);
        if(optionalEntity.isEmpty()) throw new DoNotExistException(String.format("%s with id %d does not exist", recordName(), id));
        return mapper.map(optionalEntity.get(), dtoClass);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public List<DTO> findAll(){
        List<E> entities = repository.findAll();
        return entities.stream().map(entity -> mapper.map(entity, dtoClass)).toList();
    }

    public CustomPageResponse<E,DTO> dynamicSearch(String searchTerm , Pageable pageable){
        Page<E> entities = repository.dynamicSearch(searchTerm, pageable);
        return new CustomPageResponse<>(entities , dtoClass);
    }

}
