package com.dil3kx._laba.service;

import com.dil3kx._laba.model.Visitor;
import com.dil3kx._laba.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с посетителями. Содержит бизнес-логику.
 * Требования:
 * - внедрить соответствующий репозиторий,
 * - методы: save, remove, findAll.
 *
 * Аннотация @Service указывает, что это слой бизнес-логики.
 * Внедрение зависимости через конструктор (поле private final).
 */
@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    /**
     * Конструктор для внедрения репозитория.
     * @param visitorRepository репозиторий посетителей
     */
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    /**
     * Сохраняет посетителя.
     * @param visitor объект посетителя
     * @return сохранённый объект
     */
    public Visitor save(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    /**
     * Удаляет посетителя по id.
     * @param id идентификатор
     */
    public void remove(Long id) {
        visitorRepository.remove(id);
    }

    /**
     * Возвращает список всех посетителей.
     * @return список посетителей
     */
    public List<Visitor> findAll() {
        return visitorRepository.findAll();
    }
}