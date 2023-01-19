package zup.example.zupbankmanagerexample.api.data

import jakarta.annotation.Nullable

data class AccountDataCreate(
        @field:[Nullable] val balance: String? = null
)

data class AccountDataResponse(
        val accountNumber: String
)