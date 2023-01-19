package zup.example.zupbankmanagerexample.domain

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
        @ManyToOne
        @JoinColumn(name = "ACCOUNT_NUMBER")
        val accountNumber: AccountEntity? = null,
        @Column(name = "DEPOSIT")
        val deposit: BigDecimal? = null,
        @Column(name = "WITHDRAW")
        val withdraw: BigDecimal? = null,
        @Column(name = "ACCOUNT_NUMBER_TO")
        val accountNumberTo: String? = null,
        @Column(name = "ACCOUNT_NUMBER_FROM")
        val accountNumberFrom: String? = null,
        @Column(name = "CREATED_AT")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "UPDATED_AT")
        val updatedAt: LocalDateTime? = null
)