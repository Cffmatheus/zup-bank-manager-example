package zup.example.zupbankmanagerexample.api

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CPF
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import zup.example.zupbankmanagerexample.api.data.CustomerDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataUpdate
import zup.example.zupbankmanagerexample.domain.CustomerEntity

interface CustomerApi {

    @ResponseStatus(CREATED)
    @ResponseBody
    @PostMapping
    fun create(
        @Valid @RequestBody request: CustomerDataCreate
    ): CustomerDataResponse

    @ResponseStatus(OK)
    @ResponseBody
    @PutMapping("/{cpf}")
    fun update(
            @NotBlank @CPF @PathVariable(name = "cpf") cpf: String,
            @Valid @RequestBody request: CustomerDataUpdate
    ): CustomerDataResponse

    @ResponseStatus(OK)
    @GetMapping("/{cpf}")
    fun find(
            @PathVariable(name = "cpf") cpf: String
    ): CustomerDataGetResponse

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{cpf}")
    fun delete(
            @PathVariable(name = "cpf") cpf: String
    )
}