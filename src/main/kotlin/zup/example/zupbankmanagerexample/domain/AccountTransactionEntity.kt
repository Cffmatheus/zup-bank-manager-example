package zup.example.zupbankmanagerexample.domain

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.JoinColumn
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDateTime

@Entity(name = "ACCOUNT_TRANSACTION")
data class AccountTransactionEntity(
        @Id
        @Column(name = "TRANSACTION_ID")
        val transactionId: BigInteger? = null,
        @Column(name = "TRANSACTION_TYPE")
        val transactionType: String? = null,
        @Column(name = "AMOUNT")
        val amount: BigDecimal? = null,
        @ManyToOne
        @JoinColumn(name = "ORIGIN_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
        val originAccountNumber: AccountEntity? = null,
        @Column(name = "DESTINATION_ACCOUNT_NUMBER")
        val destinationAccountNumber: String? = null,
        @Column(name = "CREATED_AT")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "UPDATED_AT")
        val updatedAt: LocalDateTime? = null
)