package zup.example.zupbankmanagerexample.domain

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
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
        @OneToOne(fetch = FetchType.EAGER, optional = true)
        @JoinColumn(name = "CUSTOMER_ID")
        val customer: CustomerEntity? = null,
        @OneToMany(mappedBy = "originAccountNumber", cascade = [ALL])
        val transactions: List<AccountTransactionEntity>? = emptyList(),
        @Column(name = "BALANCE")
        val balance: BigDecimal? = null,
        @Column(name = "CREATED_AT")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "UPDATED_AT")
        val updatedAt: LocalDateTime? = null
)