
package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "coord",
    "weather",
    "base",
    "main",
    "wind",
    "clouds",
    "dt",
    "sys",
    "id",
    "name",
    "cod"
})
public class Example {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("coord")
    private Coord coord;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("weather")
    private List<Weather> weather = null;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("base")
    private String base = "cmc stations";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("main")
    private Main main;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("wind")
    private Wind wind;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("clouds")
    private Clouds clouds;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("dt")
    private Integer dt = 1449303600;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sys")
    private Sys sys;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    private Integer id = 2643743;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name = "London";
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("cod")
    private Integer cod = 200;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("weather")
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("weather")
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sys")
    public Sys getSys() {
        return sys;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("cod")
    public Integer getCod() {
        return cod;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("cod")
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("coord", coord).append("weather", weather).append("base", base).append("main", main).append("wind", wind).append("clouds", clouds).append("dt", dt).append("sys", sys).append("id", id).append("name", name).append("cod", cod).append("additionalProperties", additionalProperties).toString();
    }

}
