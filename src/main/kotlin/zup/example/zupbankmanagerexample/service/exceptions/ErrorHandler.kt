package zup.example.zupbankmanagerexample.service.exceptions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(ex: ApplicationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val status = when (ex) {
            is CustomerAlreadyExistsException -> CONFLICT
            is CustomerNotFoundException -> NOT_FOUND
            is AccountAlreadyExistsException -> CONFLICT
            is AccountNotFoundException -> NOT_FOUND
            is AccountIdentifierNotFoundException -> BAD_REQUEST
            else -> INTERNAL_SERVER_ERROR
        }
        val error = ErrorResponse(status.value(), ex.message)
        return ResponseEntity(error, status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(INTERNAL_SERVER_ERROR.value(), ex.message)
        return ResponseEntity(error, INTERNAL_SERVER_ERROR)
    }
}

data class ErrorResponse(val status: Int, val message: String?)