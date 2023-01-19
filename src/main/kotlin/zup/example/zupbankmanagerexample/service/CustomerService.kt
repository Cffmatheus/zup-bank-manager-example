package zup.example.zupbankmanagerexample.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import zup.example.zupbankmanagerexample.api.data.CustomerDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataUpdate
import zup.example.zupbankmanagerexample.domain.CustomerEntity
import zup.example.zupbankmanagerexample.mapper.mapToEntity
import zup.example.zupbankmanagerexample.repository.CustomerRepository
import zup.example.zupbankmanagerexample.service.exceptions.CustomerAlreadyExistsException
import zup.example.zupbankmanagerexample.service.exceptions.CustomerNotFoundException
import zup.example.zupbankmanagerexample.utils.onlyDigits
import java.time.LocalDateTime
import java.util.UUID

@Service
class CustomerService {

    @Autowired
    lateinit var repository: CustomerRepository

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun create(request: CustomerDataCreate) : String {
        logger.info("Trying to create customer with cpf: ${request.cpf}.")
        if (repository.existsByCpf(request.cpf.onlyDigits()))
            throw CustomerAlreadyExistsException("Customer with cpf ${request.cpf} already exists.")
        return repository.save(mapToEntity(generateUUID(), request)).customerId!!
    }

    fun update(cpf: String, request: CustomerDataUpdate) : String {
        logger.info("Trying to update customer with cpf: $cpf.")
        if (!repository.existsByCpf(cpf.onlyDigits()))
            throw CustomerNotFoundException("Customer with cpf $cpf not found.")
        val customerSaved = repository.findByCpf(cpf.onlyDigits())
        return repository.save(customerSaved.copy(
                name = request.name?: customerSaved.name,
                email = request.email?: customerSaved.email,
                birthDate = request.birthDate?: customerSaved.birthDate,
                updatedAt = LocalDateTime.now()
        )).customerId!!
    }

    fun find(cpf: String) : CustomerEntity {
        logger.info("Trying to find customer with cpf: $cpf.")
        return repository.findByCpf(cpf.onlyDigits())
    }

    fun delete(cpf: String) {
        logger.info("Trying to delete customer with cpf: $cpf.")
        val customerSaved = repository.findByCpf(cpf.onlyDigits())
        repository.deleteById(customerSaved.customerId!!)
    }

    private fun generateUUID() : String{
        return UUID.randomUUID().toString()
    }
}