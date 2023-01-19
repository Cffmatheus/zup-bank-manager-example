package zup.example.zupbankmanagerexample.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import zup.example.zupbankmanagerexample.api.data.AccountDataCreate
import zup.example.zupbankmanagerexample.domain.AccountEntity
import zup.example.zupbankmanagerexample.mapper.mapToEntity
import zup.example.zupbankmanagerexample.repository.AccountRepository
import zup.example.zupbankmanagerexample.service.exceptions.AccountAlreadyExistsException
import zup.example.zupbankmanagerexample.utils.generateUUID

@Service
class AccountService {

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var customerService: CustomerService

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun create(cpf: String, request: AccountDataCreate): String {
        logger.info("Trying to create account to customer with cpf: $cpf")
        val customer = customerService.find(cpf)
        if (accountRepository.existsByCustomerCustomerId(customer.customerId!!))
            throw AccountAlreadyExistsException("Account already exists for customer with cpf $cpf.")
        return accountRepository.save(mapToEntity(generateUUID(), request, customer)).accountNumber!!
    }
}