package net.planeteronde.bernard.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import net.planeteronde.bernard.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
