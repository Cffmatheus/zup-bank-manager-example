package zup.example.zupbankmanagerexample.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import zup.example.zupbankmanagerexample.api.data.AccountDataCreate
import zup.example.zupbankmanagerexample.api.data.AccountDataRequest
import zup.example.zupbankmanagerexample.api.data.Identifier.CPF
import zup.example.zupbankmanagerexample.api.data.Identifier.ACCOUNT_NUMBER
import zup.example.zupbankmanagerexample.api.data.Identifier.CUSTOMER_ID
import zup.example.zupbankmanagerexample.domain.AccountEntity
import zup.example.zupbankmanagerexample.mapper.mapToAccountEntity
import zup.example.zupbankmanagerexample.repository.AccountRepository
import zup.example.zupbankmanagerexample.service.exceptions.AccountAlreadyExistsException
import zup.example.zupbankmanagerexample.service.exceptions.AccountNotFoundException
import zup.example.zupbankmanagerexample.utils.generateUUID

@Service
class AccountService {

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var customerService: CustomerService

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun create(cpf: String, request: AccountDataCreate?): String {
        logger.info("Trying to create account to customer with cpf: $cpf")
        val customer = customerService.find(cpf)
        if (accountRepository.existsByCustomerCustomerId(customer.customerId!!))
            throw AccountAlreadyExistsException("Account already exists for customer with cpf $cpf.")
        return accountRepository.save(mapToAccountEntity(generateUUID(), request, customer)).accountNumber!!
    }

    fun update(accountEntity: AccountEntity): AccountEntity {
        return accountRepository.save(accountEntity)
    }

    fun find(accountDataRequest: AccountDataRequest): AccountEntity {
        val (identifier, valueIdentifier) = accountDataRequest.throughWhichIdentifier()
        return when (identifier) {
            CPF -> findByCustomerCpf(valueIdentifier)
            ACCOUNT_NUMBER -> findByAccountNumber(valueIdentifier)
            CUSTOMER_ID -> findByCustomerCustomerId(valueIdentifier)
        }
    }

    @Transactional
    fun delete(accountDataRequest: AccountDataRequest) {
        val (identifier, valueIdentifier) = accountDataRequest.throughWhichIdentifier()
        logger.info("Trying to delete account through $identifier: $valueIdentifier")
        return when (identifier) {
            CPF -> accountRepository.deleteByCustomerCpf(valueIdentifier)
            ACCOUNT_NUMBER -> accountRepository.deleteByAccountNumber(valueIdentifier)
            CUSTOMER_ID -> accountRepository.deleteByCustomerCustomerId(valueIdentifier)
        }
    }

    fun findByCustomerCpf(cpf: String) : AccountEntity {
        logger.info("Trying to find account through CPF: $cpf")
        return accountRepository.findByCustomerCpf(cpf).orElseThrow {
            AccountNotFoundException("Account not found with CPF: $cpf.")
        }
    }

    fun findByAccountNumber(accountNumber: String) : AccountEntity {
        logger.info("Trying to find account through accout number: $accountNumber")
        return accountRepository.findById(accountNumber).orElseThrow {
            AccountNotFoundException("Account not found with account number: $accountNumber.")
        }
    }

    fun findByCustomerCustomerId(customerId: String) : AccountEntity {
        logger.info("Trying to find account through customer id: $customerId")
        return accountRepository.findByCustomerCustomerId(customerId).orElseThrow {
            AccountNotFoundException("Account not found with customer id: $customerId.")
        }
    }

    fun existsByAccountNumber(accountNumber: String) : Boolean {
        logger.info("Trying to verify if account $accountNumber exists.")
        return accountRepository.existsById(accountNumber)
    }
}