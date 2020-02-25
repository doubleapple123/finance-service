package io.myfunstuff.stocks.service.database;

import io.myfunstuff.stocks.model.StockModels.StockStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<StockStatistics,Long>{
}
