package project.khusainov.handbook.country.view;

import io.swagger.annotations.ApiModelProperty;

public class CountryView {
    @ApiModelProperty(value = "Id страны из справочника", example = "1")
    public Long id;

    @ApiModelProperty(value = "Код страны", example = "643")
    public Integer countryCode;

    @ApiModelProperty(value = "Название страны", example = "Россия")
    public String countryName;

    public CountryView(Long id, Integer countryCode, String countryName) {
        this.id = id;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }
}