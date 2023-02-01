package zup.example.zupbankmanagerexample.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zup.example.zupbankmanagerexample.api.AccountTransactionApi
import zup.example.zupbankmanagerexample.api.data.AccountTransactionRequestData
import zup.example.zupbankmanagerexample.api.data.AccountTransactionResponseData
import zup.example.zupbankmanagerexample.api.data.AccountTransactionStatementResponseData
import zup.example.zupbankmanagerexample.controller.ControllerMapper.toAccountAccountTransactionStatementResponse
import zup.example.zupbankmanagerexample.controller.ControllerMapper.toAccountTransactionResponse
import zup.example.zupbankmanagerexample.service.AccountTransactionService

@RestController
@RequestMapping("/account-transaction")
class AccountTransactionController : AccountTransactionApi {

    @Autowired
    lateinit var accountTransactionService: AccountTransactionService

    override fun withdraw(accountNumber: String, request: AccountTransactionRequestData): AccountTransactionResponseData {
        return toAccountTransactionResponse(
                accountTransactionService.withdraw(accountNumber, request),
                message = "Successful Withdraw!")
    }

    override fun deposit(accountNumber: String, request: AccountTransactionRequestData): AccountTransactionResponseData {
        return toAccountTransactionResponse(
                accountTransactionService.deposit(accountNumber, request),
                message = "Successful Deposit!"
        )
    }

    override fun transfer(originAccountNumber: String, destinationAccountNumber: String, request: AccountTransactionRequestData): AccountTransactionResponseData {
        return toAccountTransactionResponse(
                accountTransactionService.transfer(
                        originAccountNumber = originAccountNumber,
                        destinationAccountNumber = destinationAccountNumber,
                        request = request),
                message = "Successful Transfer!"
        )
    }

    override fun getAccountStatement(accountNumber: String): AccountTransactionStatementResponseData {
        return toAccountAccountTransactionStatementResponse(
                accountTransactionService.getAccountStatement(accountNumber)
        )
    }
}