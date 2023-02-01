package zup.example.zupbankmanagerexample.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zup.example.zupbankmanagerexample.api.CustomerApi
import zup.example.zupbankmanagerexample.api.data.CustomerDataCreate
import zup.example.zupbankmanagerexample.api.data.CustomerDataGetResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataResponse
import zup.example.zupbankmanagerexample.api.data.CustomerDataUpdate
import zup.example.zupbankmanagerexample.controller.ControllerMapper.toResponse
import zup.example.zupbankmanagerexample.service.CustomerService

@RequestMapping("/customer")
@RestController
class CustomerController: CustomerApi {

    @Autowired
    lateinit var customerService: CustomerService

    override fun create(request: CustomerDataCreate): CustomerDataResponse {
        return toResponse(customerService.create(request))
    }

    override fun update(cpf: String, request: CustomerDataUpdate): CustomerDataResponse {
        return toResponse(customerService.update(cpf, request))
    }

    override fun find(cpf: String): CustomerDataGetResponse {
        return toResponse(customerService.find(cpf))
    }

    override fun delete(cpf: String) {
        customerService.delete(cpf)
    }

}