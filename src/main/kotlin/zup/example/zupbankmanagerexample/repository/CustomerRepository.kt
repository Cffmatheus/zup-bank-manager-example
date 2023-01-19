package zup.example.zupbankmanagerexample.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import zup.example.zupbankmanagerexample.domain.CustomerEntity

@Repository
interface CustomerRepository: JpaRepository<CustomerEntity, String> {
    fun findByCpf(cpf: String): CustomerEntity

    fun existsByCpf(cpf: String): Boolean
}