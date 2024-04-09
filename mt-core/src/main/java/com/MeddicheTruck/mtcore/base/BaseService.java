package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.annotations.FilterDtoFields;
import com.MeddicheTruck.mtcore.controllers.CustomPageResponse;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.ValidationException;
import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import com.MeddicheTruck.mtcore.services.validations.BaseValidation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

// Mapping Source :https://www.baeldung.com/java-dto-pattern

public abstract class BaseService<E extends BaseEntity, I_DTO extends BaseEntityDto , O_DTO extends BaseEntityDto , R extends BaseRepository<E> > extends BaseValidation implements BaseServiceInterface<E, I_DTO , O_DTO> {

    abstract protected String recordName();


    protected R repository;

    protected Class<E> entityClass;

    protected Class<I_DTO> i_dtoClass;

    protected Class<O_DTO> o_dtoClass;

    protected final ModelMapper mapper = new ModelMapper();

    @Autowired
    protected BaseService(R repository , Class<E> entityClass , Class<I_DTO> i_dtoClass , Class<O_DTO> o_dtoClass ){
        this.repository = repository;
        this.entityClass = entityClass;
        this.i_dtoClass = i_dtoClass;
        this.o_dtoClass = o_dtoClass;
    }

    public List<O_DTO> saveAll(List<I_DTO> entities){
        List<E> entitiesToSave = entities.stream().map(dto -> mapper.map(dto, entityClass)).toList();
        List<E> savedEntities = repository.saveAll(entitiesToSave);
        return savedEntities.stream().map(entity -> mapper.map(entity, o_dtoClass)).toList();
    }

    public O_DTO save(I_DTO entityDto){
        saveValidation(entityDto);
        E entityToSave = mapper.map(entityDto, entityClass);
        beforeSave(entityToSave , entityDto);
        E  savedEntity = repository.save(entityToSave);
        return mapper.map(savedEntity, o_dtoClass);
    }

    public void saveValidation(I_DTO dto){
        globalValidation(dto);
    }
    public void beforeSave(E entity , I_DTO dto){}

    public O_DTO update(I_DTO dto){
        updateValidation(dto);
        // Why new ModelMapper() ?
        // Because the mapper is a stateful object, and we need to create a new instance of it to avoid any side effects.
        // Example : You can't create a new TypeMap for the same classes with different configurations.
        ModelMapper mapper = new ModelMapper();

        // https://www.baeldung.com/java-modelmapper#2-auto-skip-null-properties
        mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<I_DTO, E> propertyMap = mapper.createTypeMap(i_dtoClass, entityClass);
        propertyMap.setProvider(p -> findByIdEntity(dto.getId()));
        // ---

        E entityToUpdate = mapper.map(dto, entityClass);
        beforeUpdate(entityToUpdate , dto);
        E updatedEntity = repository.save(entityToUpdate);
        return mapper.map(updatedEntity, o_dtoClass);
    }

    public void updateValidation(I_DTO dto){
        globalValidation(dto);
    }
    public void beforeUpdate(E entity , I_DTO dto){}

    public void deleteById(Long id){
        deleteValidation(id);
        beforeDelete(id);
        repository.deleteById(id);
    }

    public void globalValidation(I_DTO dto){
        validateObject(dto);
    }

    public void deleteValidation(Long id){
        idNullValidation(id);
        idDoNotExistValidation(id);
    }
    public void beforeDelete(Long id){}

    public O_DTO findById(Long id){
        idNullValidation(id);
        Optional<E> optionalEntity = repository.findById(id);
        entityEmptyValidation(optionalEntity);
        return mapper.map(optionalEntity.get(), o_dtoClass);
    }

    public E findByIdEntity(Long id){
        idNullValidation(id);
        Optional<E> optionalEntity = repository.findById(id);
        entityEmptyValidation(optionalEntity);
        return optionalEntity.get();
    }

    public Boolean existsById(Long id) {
        idNullValidation(id);
        return repository.existsById(id);
    }

    public List<O_DTO> findAll(){
        List<E> entities = repository.findAll();
        return entities.stream().map(entity -> mapper.map(entity, o_dtoClass)).toList();
    }

    public CustomPageResponse<E,O_DTO> dynamicSearch(String searchTerm , Pageable pageable){
        Page<E> entities = repository.dynamicSearch(searchTerm, pageable);
        return new CustomPageResponse<>(entities , o_dtoClass);
    }

    public void idNullValidation(Long id){
        throwExceptionIf(ID_IS_NULL, id, ValidationException::new, String.format("The id of %s can't be null", recordName()));
    }

    public void idDoNotExistValidation(Long id){
        throwExceptionIf(ID_DO_NOT_EXISTS, id, DoNotExistException::new, String.format("%s with id %d does not exist", recordName(), id));
    }

    public void entityEmptyValidation(Optional<E> entity){
        throwExceptionIf(ENTITY_IS_EMPTY, entity, DoNotExistException::new, String.format("%s does not exist", recordName()));
    }

    Predicate<Long> ID_DO_NOT_EXISTS = id -> !existsById(id);

    Predicate<Long> ID_IS_NULL = Objects::isNull;

    Predicate<Optional<E>> ENTITY_IS_EMPTY = Optional::isEmpty;


}
