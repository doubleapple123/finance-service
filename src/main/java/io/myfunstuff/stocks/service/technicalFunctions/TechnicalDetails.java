package io.myfunstuff.stocks.service.technicalFunctions;

public interface TechnicalDetails {
    float end = 0;
    float start = 0;
    float slope = 0;

    void setEnd(float i);
    void setStart(float i);
    void setSlope(float i);

}
