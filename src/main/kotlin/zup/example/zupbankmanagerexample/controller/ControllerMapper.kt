package zup.example.zupbankmanagerexample.controller

import zup.example.zupbankmanagerexample.api.data.AccountDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerFromAccountDataGetResponse
import zup.example.zupbankmanagerexample.api.data.AccountDataResponse
import zup.example.zupbankmanagerexample.api.data.AccountFromCustomerDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataResponse
import zup.example.zupbankmanagerexample.domain.AccountEntity
import zup.example.zupbankmanagerexample.domain.CustomerEntity

object ControllerMapper {

    internal fun toResponse(customerId: String): CustomerDataResponse {
        return CustomerDataResponse(customerId)
    }

    internal fun toResponse(customerEntity: CustomerEntity): CustomerDataGetResponse {
        return CustomerDataGetResponse(
                customerId = customerEntity.customerId,
                name = customerEntity.name,
                cpf = customerEntity.cpf,
                email = customerEntity.email,
                birthDate = customerEntity.birthDate,
                account = accountFromCustomer(customerEntity.account!!)
        )
    }

    private fun accountFromCustomer(accountEntity: AccountEntity) : AccountFromCustomerDataGetResponse {
        return AccountFromCustomerDataGetResponse(
                accountNumber = accountEntity.accountNumber,
                balance = accountEntity.balance
        )
    }

    internal fun toAccountResponse(accountNumber: String): AccountDataResponse {
        return AccountDataResponse(accountNumber)
    }

    internal fun toAccountGetResponse(accountEntity: AccountEntity): AccountDataGetResponse {
        return AccountDataGetResponse(
                accountNumber = accountEntity.accountNumber,
                customer = customerFromAccount(accountEntity.customer!!),
                balance = accountEntity.balance
        )
    }

    private fun customerFromAccount(customerEntity: CustomerEntity): CustomerFromAccountDataGetResponse {
        return CustomerFromAccountDataGetResponse(
                customerId = customerEntity.customerId,
                cpf = customerEntity.cpf,
                name = customerEntity.name,
                email = customerEntity.email,
                birthDate = customerEntity.birthDate
        )
    }
}