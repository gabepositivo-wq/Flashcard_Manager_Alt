package org.flashCardManager.jsonRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flashCardManager.exceptions.NotFoundException;
import org.flashCardManager.exceptions.PersistenceException;
import org.flashCardManager.model.entity.Identifiable;
import org.flashCardManager.repository.CrudRepository;
import org.flashCardManager.util.JacksonUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJsonRepository <T extends Identifiable> implements CrudRepository<T> {

    protected final String filePath;
    private final Class<T> entityClass;

    protected AbstractJsonRepository(String filePath, Class<T> entityClass) {
        this.filePath = filePath;
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        return readFile();
    }

    @Override
    public Optional<T> findById(String id) {
        return readFile()
                .stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }

    @Override
    public T save(T entity) {
        List<T> entities = readFile();
        entities.add(entity);
        writeFile(entities);
        return entity;
    }

    @Override
    public T update(T entity) {
        List<T> entities = readFile();

        for(int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getId().equals(entity.getId())) {
                entities.set(i, entity);
                writeFile(entities);

                return  entity;
            }
        }
        throw new IllegalArgumentException("Entidade não encontrada.");
    }

    @Override
    public void deleteById(String id) {

        List<T> entities = readFile();

        boolean removed = entities.removeIf(
                entity -> entity.getId().equals(id)
        );

        if (!removed) {
            throw new NotFoundException("Entidade não encontrada.");
        }

        writeFile(entities);
    }

    protected List<T> readFile() {
        ObjectMapper mapper = JacksonUtil.getObjectMapper();

        File file = new File(filePath);

        if(!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(
                    file,
                    mapper
                        .getTypeFactory()
                        .constructCollectionType(
                            List.class,
                            entityClass
                        )
                    );
        } catch (IOException e) {
           throw new PersistenceException("Erro ao ler o arquivo");
        }
    }

    protected void writeFile(List<T> entities) {
        ObjectMapper mapper = JacksonUtil.getObjectMapper();

        File file = new File(filePath);

        try {
            mapper.writeValue(file, entities);
        } catch (IOException e) {
            throw new PersistenceException("Erro ao persistir dados");
        }
    }
}
