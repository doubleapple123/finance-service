package io.myfunstuff.stocks.service.database;

import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import org.springframework.data.repository.CrudRepository;

public interface StockRepo extends CrudRepository<TimeSeriesDataCollection,Integer>{
}
