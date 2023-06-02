package org.hw_4.repository;

import org.hw_4.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Modifying
    @Query("update Item i set i.price = 200 where i.itemName='Vacuum cleaner'")
    public void setPriceForVacuumCleaner();
}
