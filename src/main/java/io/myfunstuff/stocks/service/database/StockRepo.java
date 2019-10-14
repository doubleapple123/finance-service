package io.myfunstuff.stocks.service.database;

import io.myfunstuff.stocks.model.StockStatistics;
import org.springframework.data.repository.CrudRepository;

public interface StockRepo extends CrudRepository<StockStatistics,String>{
}
