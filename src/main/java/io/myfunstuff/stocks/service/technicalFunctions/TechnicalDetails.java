package io.myfunstuff.stocks.service.technicalFunctions;

import io.myfunstuff.stocks.model.StockStatistics;

public interface TechnicalDetails {
    float end = 0;
    float start = 0;
    float slope = 0;

    void setEnd(float i);
    void setStart(float i);
    void setSlope(float i);

}
