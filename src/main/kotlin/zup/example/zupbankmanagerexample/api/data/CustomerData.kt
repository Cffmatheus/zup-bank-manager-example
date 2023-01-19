package zup.example.zupbankmanagerexample.api.data

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.PastOrPresent
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import java.time.LocalDate


data class CustomerDataCreate(
        @field:[NotEmpty] val name: String,
        @field:[NotEmpty CPF] val cpf: String,
        @field:[NotEmpty Email] val email: String,
        @field:[PastOrPresent] val birthDate: LocalDate
)

data class CustomerDataUpdate(
        @field:[NotBlank] val name: String?,
        @field:[Email] val email: String?,
        @field:[PastOrPresent] val birthDate: LocalDate?
)

data class CustomerDataResponse(
        val customerId: String
)

data class CustomerDataGetResponse(
        val customerId: String?,
        val name: String?,
        val cpf: String?,
        val email: String?,
        val birthDate: LocalDate?,
        val account: AccountFromCustomerDataGetResponse? = null
)

data class AccountFromCustomerDataGetResponse(
        val accountNumber: String? = null,
        val balance: BigDecimal? = null
)