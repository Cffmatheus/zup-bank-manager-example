package zup.example.zupbankmanagerexample.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import zup.example.zupbankmanagerexample.domain.AccountEntity

@Repository
interface AccountRepository : JpaRepository<AccountEntity, String> {
    fun existsByCustomerCustomerId(customerId: String): Boolean
}