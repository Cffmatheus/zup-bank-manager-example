package zup.example.zupbankmanagerexample.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import zup.example.zupbankmanagerexample.api.data.AccountTransactionRequestData
import zup.example.zupbankmanagerexample.domain.AccountTransactionEntity
import zup.example.zupbankmanagerexample.domain.AccountTransactionType
import zup.example.zupbankmanagerexample.repository.AccountTransactionRepository
import zup.example.zupbankmanagerexample.service.exceptions.AccountNotFoundException
import zup.example.zupbankmanagerexample.service.exceptions.InsufficientFundsException
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class AccountTransactionService {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var accountTransactionRepository: AccountTransactionRepository

    @Autowired
    lateinit var accountService: AccountService

    @Transactional
    fun withdraw(accountNumber: String, request: AccountTransactionRequestData) : AccountTransactionEntity {
        logger.info("Trying to withdraw ${request.amount} from account $accountNumber.")
        val accountEntity = accountService.findByAccountNumber(accountNumber)
        val amountToWithdraw = BigDecimal(request.amount)
        val newBalance = accountEntity.balance!! - amountToWithdraw
        if (!withdrawIsPossible(amountToWithdraw, accountEntity.balance))
            throw InsufficientFundsException("Insufficient founds. Your balance is ${accountEntity.balance}")
        accountService.update(accountEntity.copy(
                balance = newBalance,
                updatedAt = LocalDateTime.now()
        ))
        return accountTransactionRepository.save(
                AccountTransactionEntity(
                        transactionType = AccountTransactionType.WITHDRAW.toString(),
                        amount = amountToWithdraw,
                        originAccountNumber = accountEntity
                )
        )
    }

    @Transactional
    fun deposit(accountNumber: String, request: AccountTransactionRequestData) : AccountTransactionEntity {
        logger.info("Trying to deposit ${request.amount} from account $accountNumber.")
        val accountEntity = accountService.findByAccountNumber(accountNumber)
        val amountToDeposit = BigDecimal(request.amount)
        val newBalance = accountEntity.balance!! + amountToDeposit
        accountService.update(accountEntity.copy(
                balance = newBalance,
                updatedAt = LocalDateTime.now()
        ))
        return accountTransactionRepository.save(
                AccountTransactionEntity(
                        transactionType = AccountTransactionType.DEPOSIT.toString(),
                        amount = amountToDeposit,
                        originAccountNumber = accountEntity
                )
        )
    }

    @Transactional
    fun transfer(
            originAccountNumber: String,
            destinationAccountNumber: String,
            request: AccountTransactionRequestData)
    : AccountTransactionEntity {
        logger.info("Trying to transfer ${request.amount} " +
                "from account $originAccountNumber to account $destinationAccountNumber.")
        val originAccount = accountService.findByAccountNumber(originAccountNumber)
        val destinationAccount = accountService.findByAccountNumber(destinationAccountNumber)
        val amountToTransfer = BigDecimal(request.amount)
        if (!withdrawIsPossible(amountToTransfer, originAccount.balance!!))
            throw InsufficientFundsException("Insufficient founds. Your balance is ${originAccount.balance}")
        accountService.update(originAccount.copy(
                balance = originAccount.balance - amountToTransfer,
                updatedAt = LocalDateTime.now()
        ))
        accountService.update(destinationAccount.copy(
                balance = destinationAccount.balance!! + amountToTransfer,
                updatedAt = LocalDateTime.now()
        ))
        return accountTransactionRepository.save(
                AccountTransactionEntity(
                        transactionType = AccountTransactionType.TRANSFER.toString(),
                        amount = amountToTransfer,
                        originAccountNumber = originAccount,
                        destinationAccountNumber = destinationAccount.accountNumber
                )
        )
    }

    fun getAccountStatement(accountNumber: String) : List<AccountTransactionEntity> {
        logger.info("Trying to get account statement from account $accountNumber.")
        if (!accountService.existsByAccountNumber(accountNumber))
            throw AccountNotFoundException("Account $accountNumber not found.")
        return accountTransactionRepository.findAllByOriginAccountNumberAccountNumber(accountNumber).orElse(emptyList())
    }

    private fun withdrawIsPossible(amount: BigDecimal, balance: BigDecimal) = balance-amount >= BigDecimal.ZERO

}