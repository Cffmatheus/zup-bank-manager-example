package zup.example.zupbankmanagerexample.api

import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import zup.example.zupbankmanagerexample.api.data.AccountDataCreate
import zup.example.zupbankmanagerexample.api.data.AccountDataRequest
import zup.example.zupbankmanagerexample.api.data.AccountDataGetResponse
import zup.example.zupbankmanagerexample.api.data.AccountDataResponse

interface AccountApi {

    @ResponseStatus(CREATED)
    @ResponseBody
    @PostMapping
    fun create(
            @RequestParam(name = "cpf", required = true) cpf: String,
            @Valid @RequestBody(required = false) request: AccountDataCreate?
    ): AccountDataResponse

    @ResponseStatus(OK)
    @ResponseBody
    @GetMapping
    fun find(
            @Valid @RequestBody request: AccountDataRequest
    ): AccountDataGetResponse

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping
    fun delete(
            @Valid @RequestBody request: AccountDataRequest
    )
}