package zup.example.zupbankmanagerexample.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import zup.example.zupbankmanagerexample.domain.AccountEntity
import java.util.*

@Repository
interface AccountRepository : JpaRepository<AccountEntity, String> {
    fun existsByCustomerCustomerId(customerId: String): Boolean
    fun findByCustomerCpf(cpf: String): Optional<AccountEntity>
    fun findByCustomerCustomerId(customerId: String): Optional<AccountEntity>
    @Transactional
    @Query(name = "AccountEntity.deleteByCustomerCpf", nativeQuery = true)
    @Modifying
    fun deleteByCustomerCpf(cpf: String)
    @Transactional
    @Query(name = "AccountEntity.deleteByCustomerCustomerId", nativeQuery = true)
    @Modifying
    fun deleteByCustomerCustomerId(customerId: String)
    @Transactional
    @Query(name = "AccountEntity.deleteByAccountNumber", nativeQuery = true)
    @Modifying
    fun deleteByAccountNumber(accountNumber: String)
}