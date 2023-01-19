package zup.example.zupbankmanagerexample.mapper

import zup.example.zupbankmanagerexample.api.data.CustomerDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataUpdate
import zup.example.zupbankmanagerexample.domain.CustomerEntity
import zup.example.zupbankmanagerexample.utils.onlyDigits

fun mapToEntity(
        customerId: String,
        customerDataCreate: CustomerDataCreate
): CustomerEntity {

    return CustomerEntity(
            customerId = customerId,
            name = customerDataCreate.name,
            cpf = customerDataCreate.cpf.onlyDigits(),
            email = customerDataCreate.email,
            birthDate = customerDataCreate.birthDate
    )
}

fun mapToEntity(
        customerDataUpdate: CustomerDataUpdate
): CustomerEntity {
    return CustomerEntity(

    )
}