package zup.example.zupbankmanagerexample.api

import jakarta.validation.Valid
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import zup.example.zupbankmanagerexample.api.data.AccountTransactionStatementResponseData
import zup.example.zupbankmanagerexample.api.data.AccountTransactionRequestData
import zup.example.zupbankmanagerexample.api.data.AccountTransactionResponseData

interface AccountTransactionApi {

    @ResponseStatus(OK)
    @ResponseBody
    @PutMapping("/withdraw/{accountNumber}")
    fun withdraw(
            @PathVariable(name = "accountNumber") accountNumber: String,
            @Valid @RequestBody request: AccountTransactionRequestData
    ): AccountTransactionResponseData

    @ResponseStatus(OK)
    @ResponseBody
    @PutMapping("/deposit/{accountNumber}")
    fun deposit(
            @PathVariable(name = "accountNumber") accountNumber: String,
            @Valid @RequestBody request: AccountTransactionRequestData
    ): AccountTransactionResponseData

    @ResponseStatus(OK)
    @ResponseBody
    @PutMapping("/transfer/{originAccountNumber}")
    fun transfer(
            @PathVariable(name = "originAccountNumber") originAccountNumber: String,
            @RequestParam(name = "destinationAccountNumber", required = true) destinationAccountNumber: String,
            @Valid @RequestBody request: AccountTransactionRequestData
    ): AccountTransactionResponseData

    @ResponseStatus(OK)
    @ResponseBody
    @GetMapping("/account-statement/{accountNumber}")
    fun getAccountStatement(
        @PathVariable(name = "accountNumber") accountNumber: String
    ): AccountTransactionStatementResponseData
}