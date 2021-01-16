package br.com.vsmo.forumapi.utils.validation;

public class FormErrorDTO {

  private String field;
  private String messageError;

  public FormErrorDTO(String field, String messageError) {
    this.field = field;
    this.messageError = messageError;
  }

  public String getField() {
    return field;
  }

  public String getMessageError() {
    return messageError;
  }

}
