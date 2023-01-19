package zup.example.zupbankmanagerexample.api.data

import jakarta.annotation.Nullable
import zup.example.zupbankmanagerexample.service.exceptions.AccountIdentifierNotFoundException
import java.math.BigDecimal
import java.time.LocalDate

data class AccountDataCreate(
        @field:[Nullable] val balance: String? = null
)

data class AccountDataResponse(
        val accountNumber: String
)

data class AccountDataRequest(
        val cpf: String?,
        val customerId: String?,
        val accountNumber: String?
) {
    private fun isCpf() = cpf?.isNotBlank() == true && cpf.isNotBlank()
    private fun isCustomerId() = customerId?.isNotBlank() == true && customerId.isNotBlank()
    private fun isAccountNumber() = accountNumber?.isNotBlank() == true && accountNumber.isNotBlank()
    fun throughWhichIdentifier(): Pair<Identifier, String> {
        return if (isCpf()) {
            Pair(Identifier.CPF, cpf!!)
        } else if (isAccountNumber()) {
            Pair(Identifier.ACCOUNT_NUMBER, accountNumber!!)
        } else if (isCustomerId()) {
            Pair(Identifier.CUSTOMER_ID, customerId!!)
        } else {
            throw AccountIdentifierNotFoundException("Bad Request. One of the values need to be assigned. " +
                    "Cpf, accountNumber or customerId")
        }
    }
}
data class AccountDataGetResponse(
        val accountNumber: String?,
        val customer: CustomerFromAccountDataGetResponse?,
        val balance: BigDecimal?
)

data class CustomerFromAccountDataGetResponse(
        val customerId: String?,
        val name: String?,
        val cpf: String?,
        val email: String?,
        val birthDate: LocalDate?
)

enum class Identifier {
    CPF, ACCOUNT_NUMBER, CUSTOMER_ID
}