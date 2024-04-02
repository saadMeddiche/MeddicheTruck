package com.MeddicheTruck.mtcore.base;

import com.MeddicheTruck.mtcore.handlingExceptions.costumExceptions.DoNotExistException;
import com.MeddicheTruck.mtcore.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends BaseEntity, R extends BaseRepository> implements BaseServiceInterface<E> {

    abstract protected String recordName();

    public R repository;

    @Autowired
    public BaseService(R repository){
        this.repository = repository;
    }

    public List<E> saveAll(List<Long> ids){
        return repository.findAllById(ids);
    }
    public E save(E entity){
        return (E) repository.save(entity);
    }

    public E update(E entity){
        return (E) repository.save(entity);
    }

    public void delete(E entity){
        repository.delete(entity);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public E findById(Long id){
        Optional<E> optionalEntity = repository.findById(id);
        return optionalEntity.orElseThrow(() -> new DoNotExistException(String.format("%s with id %d does not exist", recordName(), id)));
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public List<E> findAll(){
        return repository.findAll();
    }

    public Page<E> dynamicSearch(String searchTerm , Pageable pageable){
        return repository.dynamicSearch(searchTerm ,pageable);
    }

}
