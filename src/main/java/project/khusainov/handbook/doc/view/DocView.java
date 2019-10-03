package project.khusainov.handbook.doc.view;

import io.swagger.annotations.ApiModelProperty;

public class DocView {
    @ApiModelProperty(value = "Id типа документа", example = "1")
    public Long id;

    @ApiModelProperty(value = "Код документа", example = "21")
    public Integer docCode;

    @ApiModelProperty(value = "Название документа", example = "Паспорт гражданина Российской Федерации")
    public String docName;

    public DocView(Long id, Integer docCode, String docName) {
        this.id = id;
        this.docCode = docCode;
        this.docName = docName;
    }
}