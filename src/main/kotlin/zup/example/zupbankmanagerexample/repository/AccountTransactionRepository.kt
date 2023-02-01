package zup.example.zupbankmanagerexample.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import zup.example.zupbankmanagerexample.domain.AccountTransactionEntity
import java.math.BigInteger
import java.util.*

@Repository
interface AccountTransactionRepository : JpaRepository<AccountTransactionEntity, BigInteger>{

    fun findAllByOriginAccountNumberAccountNumber(accountNumber: String): Optional<List<AccountTransactionEntity>>
}