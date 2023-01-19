package zup.example.zupbankmanagerexample.service.exceptions

open class ApplicationException(message: String) : Exception(message)

class CustomerAlreadyExistsException(message: String) : ApplicationException(message)

class CustomerNotFoundException(message: String) : ApplicationException(message)

class AccountAlreadyExistsException(message: String) : ApplicationException(message)

class AccountNotFoundException(message: String) : ApplicationException(message)

class AccountIdentifierNotFoundException(message: String) : ApplicationException(message)