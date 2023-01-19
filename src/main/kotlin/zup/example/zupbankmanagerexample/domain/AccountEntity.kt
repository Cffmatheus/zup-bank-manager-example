package zup.example.zupbankmanagerexample.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "ACCOUNT")
data class AccountEntity (
        @Id
        @Column(name = "ACCOUNT_NUMBER")
        val accountNumber: String? = null,
        @OneToOne
        @JoinColumn(name = "CUSTOMER_ID")
        val customer: CustomerEntity? = null,
        @OneToMany
        val transactions: List<AccountTransactionEntity>? = null,
        @Column(name = "BALANCE")
        val balance: BigDecimal? = null,
        @Column(name = "ACTIVE")
        val active: Boolean? = null,
        @Column(name = "CREATED_AT")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "UPDATED_AT")
        val updatedAt: LocalDateTime? = null
)