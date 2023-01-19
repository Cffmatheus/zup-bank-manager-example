package zup.example.zupbankmanagerexample.mapper

import zup.example.zupbankmanagerexample.api.data.AccountDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataUpdate
import zup.example.zupbankmanagerexample.domain.AccountEntity
import zup.example.zupbankmanagerexample.domain.CustomerEntity
import zup.example.zupbankmanagerexample.utils.onlyDigits
import java.math.BigDecimal

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

fun mapToAccountEntity(
        accountNumber: String,
        accountDataCreate: AccountDataCreate?,
        customerEntity: CustomerEntity
) : AccountEntity {
    return AccountEntity(
            accountNumber = accountNumber,
            balance = accountDataCreate?.balance?.toBigDecimal()?: BigDecimal.ZERO,
            customer = customerEntity
    )
}