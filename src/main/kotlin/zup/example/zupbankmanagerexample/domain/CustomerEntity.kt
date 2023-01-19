package zup.example.zupbankmanagerexample.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "CUSTOMER")
data class CustomerEntity(
        @Id
        @Column(name = "CUSTOMER_ID")
        val customerId: String? = null,
        @OneToOne(mappedBy = "customer", cascade = [CascadeType.ALL])
        val account: AccountEntity? = null,
        @Column(name = "NAME")
        val name: String? = null,
        @Column(name = "CPF")
        val cpf: String? = null,
        @Column(name = "EMAIL")
        val email: String? = null,
        @Column(name = "BIRTH_DATE")
        val birthDate: LocalDate? = null,
        @Column(name = "CREATED_AT")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "UPDATED_AT")
        val updatedAt: LocalDateTime? = null
)
