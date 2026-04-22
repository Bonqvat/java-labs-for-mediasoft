package com.dil3kx._laba.repository;

import com.dil3kx._laba.model.Visitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с данными о посетителях.
 * Требования:
 * - данные хранятся в приватном финальном поле типа List,
 * - методы: save, remove, findAll.
 * 
 * Аннотация @Repository указывает, что это компонент доступа к данным.
 * Хранение в памяти, без базы данных.
 */
@Repository
public class VisitorRepository {

    // Хранилище посетителей в оперативной памяти
    private final List<Visitor> visitors = new ArrayList<>();
    // Счётчик для генерации уникальных идентификаторов
    private long nextId = 1L;

    /**
     * Сохраняет посетителя. Если id == null, генерирует новый id и добавляет в список.
     * Иначе обновляет существующего (удаляет старую запись и добавляет новую).
     *
     * @param visitor объект посетителя
     * @return сохранённый объект с присвоенным id
     */
    public Visitor save(Visitor visitor) {
        if (visitor.getId() == null) {
            visitor.setId(nextId++);
            visitors.add(visitor);
        } else {
            // Обновление существующего: удаляем старую версию и добавляем новую
            deleteById(visitor.getId());
            visitors.add(visitor);
        }
        return visitor;
    }

    /**
     * Удаляет посетителя по идентификатору.
     *
     * @param id идентификатор удаляемого посетителя
     */
    public void remove(Long id) {
        visitors.removeIf(v -> v.getId().equals(id));
    }

    /**
     * Возвращает список всех посетителей (копию внутреннего списка).
     *
     * @return список посетителей
     */
    public List<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }

    /**
     * Вспомогательный метод для поиска посетителя по id.
     *
     * @param id идентификатор
     * @return Optional с посетителем, если найден
     */
    public Optional<Visitor> findById(Long id) {
        return visitors.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    // Внутренний метод удаления по id (используется при обновлении)
    private void deleteById(Long id) {
        visitors.removeIf(v -> v.getId().equals(id));
    }
}