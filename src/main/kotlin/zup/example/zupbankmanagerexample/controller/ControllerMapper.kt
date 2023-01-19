package zup.example.zupbankmanagerexample.controller

import zup.example.zupbankmanagerexample.api.data.CustomerDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataResponse
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
                account = customerEntity.account
        )
    }
}