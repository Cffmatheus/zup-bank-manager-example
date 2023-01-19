package zup.example.zupbankmanagerexample.service.exceptions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(ex: ApplicationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val status = when (ex) {
            is CustomerAlreadyExistsException -> HttpStatus.CONFLICT
            is CustomerNotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        val error = ErrorResponse(status.value(), ex.message)
        return ResponseEntity(error, status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message)
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

data class ErrorResponse(val status: Int, val message: String?)