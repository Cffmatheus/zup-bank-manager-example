package zup.example.zupbankmanagerexample.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zup.example.zupbankmanagerexample.api.CustomerApi
import zup.example.zupbankmanagerexample.api.data.CustomerDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataUpdate
import zup.example.zupbankmanagerexample.controller.ControllerMapper.toResponse
import zup.example.zupbankmanagerexample.domain.CustomerEntity
import zup.example.zupbankmanagerexample.service.CustomerService

@RequestMapping("/customer")
@RestController
class CustomerController: CustomerApi {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var customerService: CustomerService

    override fun create(request: CustomerDataCreate): CustomerDataResponse {
        logger.info("Creating customer ${request.name}.")
        return toResponse(customerService.create(request))
    }

    override fun update(cpf: String, request: CustomerDataUpdate): CustomerDataResponse {
        logger.info("Trying to update customer ${request.name}.")
        return toResponse(customerService.update(cpf, request))
    }

    override fun find(cpf: String): CustomerDataGetResponse {
        logger.info("Trying to find customer with CPF: $cpf")
        return toResponse(customerService.find(cpf))
    }

    override fun delete(cpf: String) {
        logger.info("Trying to delete customer with CPF: $cpf")
        customerService.delete(cpf)
    }

}