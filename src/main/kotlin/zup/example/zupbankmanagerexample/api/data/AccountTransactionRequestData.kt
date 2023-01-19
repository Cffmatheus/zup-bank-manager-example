package zup.example.zupbankmanagerexample.api.data

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountTransactionRequestData(
        @field:[NotNull NotBlank NotEmpty] val amount: String? = null
)

data class AccountTransactionResponseData(
        val message: String,
        val transaction: AccountTransactionData,
        val balance: BigDecimal
)

data class AccountTransactionData (
        val amount: BigDecimal,
        val date: LocalDateTime
)

data class AccountTransactionWithTypeData (
        val amount: BigDecimal,
        val type: String,
        val date: LocalDateTime
)

data class AccountTransactionStatementResponseData(
        val accountNumber: String,
        val transactions: List<AccountTransactionWithTypeData>
)