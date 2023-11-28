public interface ParsedDataStrategy {
    void consumeData(SuperTempHumidReading data);
    void storeData();
    void sortData();
    SuperTempHumidReading getMiddleReading();
    SuperTempHumidReading getMiddleReadingOnDate(double date);
    void switchStrategy(ParsedDataStrategy otherStrategy);

    void clearData();

    void transferDataTo(ParsedDataStrategy otherStrategy);
}
