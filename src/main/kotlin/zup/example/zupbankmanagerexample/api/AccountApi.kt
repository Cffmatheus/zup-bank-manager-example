package zup.example.zupbankmanagerexample.api

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import zup.example.zupbankmanagerexample.api.data.AccountDataCreate
import zup.example.zupbankmanagerexample.api.data.AccountDataResponse

interface AccountApi {

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    fun create(
            @RequestParam(name = "cpf", required = true) cpf: String,
            @Valid @RequestBody request: AccountDataCreate
    ): AccountDataResponse
}