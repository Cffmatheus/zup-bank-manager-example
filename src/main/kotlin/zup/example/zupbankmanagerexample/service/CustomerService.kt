package zup.example.zupbankmanagerexample.service

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

    fun create(request: CustomerDataCreate) : String {
        if (repository.existsByCpf(request.cpf.onlyDigits()))
            throw CustomerAlreadyExistsException("Customer with cpf ${request.cpf} already exists.")
        return repository.save(mapToEntity(generateUUID(), request)).customerId!!
    }

    fun update(cpf: String, request: CustomerDataUpdate) : String {
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
        return repository.findByCpf(cpf.onlyDigits())
    }

    fun delete(cpf: String) {
        val customerSaved = repository.findByCpf(cpf.onlyDigits())
        repository.deleteById(customerSaved.customerId!!)
    }

    private fun generateUUID() : String{
        return UUID.randomUUID().toString()
    }
}