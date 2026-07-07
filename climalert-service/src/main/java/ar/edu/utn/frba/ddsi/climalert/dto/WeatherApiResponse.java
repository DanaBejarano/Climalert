package ar.edu.utn.frba.ddsi.climalert.dto;

public class WeatherApiResponse {

    private Location location;
    private Current current;

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public Current getCurrent() { return current; }
    public void setCurrent(Current current) { this.current = current; }

    public static class Location {
        private String name;
        private String region;
        private String country;
        private String localtime;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }

        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }

        public String getLocaltime() { return localtime; }
        public void setLocaltime(String localtime) { this.localtime = localtime; }
    }

    public static class Current {
        private String last_updated;
        private double temp_c;
        private double feelslike_c;
        private int humidity;
        private double wind_kph;
        private Condition condition;

        public String getLast_updated() { return last_updated; }
        public void setLast_updated(String last_updated) { this.last_updated = last_updated; }

        public double getTemp_c() { return temp_c; }
        public void setTemp_c(double temp_c) { this.temp_c = temp_c; }

        public double getFeelslike_c() { return feelslike_c; }
        public void setFeelslike_c(double feelslike_c) { this.feelslike_c = feelslike_c; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }

        public double getWind_kph() { return wind_kph; }
        public void setWind_kph(double wind_kph) { this.wind_kph = wind_kph; }

        public Condition getCondition() { return condition; }
        public void setCondition(Condition condition) { this.condition = condition; }
    }

    public static class Condition {
        private String text;
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
