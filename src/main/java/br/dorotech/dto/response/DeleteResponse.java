package br.dorotech.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteResponse {

    @JsonProperty("is_deleted")
    private boolean isDeleted;
}
