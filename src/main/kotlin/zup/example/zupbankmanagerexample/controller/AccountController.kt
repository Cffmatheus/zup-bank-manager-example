package zup.example.zupbankmanagerexample.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zup.example.zupbankmanagerexample.api.AccountApi
import zup.example.zupbankmanagerexample.api.data.AccountDataCreate
import zup.example.zupbankmanagerexample.api.data.AccountDataRequest
import zup.example.zupbankmanagerexample.api.data.AccountDataGetResponse
import zup.example.zupbankmanagerexample.api.data.AccountDataResponse
import zup.example.zupbankmanagerexample.controller.ControllerMapper.toAccountGetResponse
import zup.example.zupbankmanagerexample.controller.ControllerMapper.toAccountResponse
import zup.example.zupbankmanagerexample.service.AccountService

@RequestMapping("/account")
@RestController
class AccountController: AccountApi {

    @Autowired
    lateinit var accountService: AccountService

    override fun create(cpf: String, request: AccountDataCreate?): AccountDataResponse {
        return toAccountResponse(accountService.create(cpf, request))
    }

    override fun find(request: AccountDataRequest): AccountDataGetResponse {
        return toAccountGetResponse(accountService.find(request))
    }

    override fun delete(request: AccountDataRequest) {
        return accountService.delete(request)
    }
}