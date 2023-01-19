package zup.example.zupbankmanagerexample.domain

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
open class AbstractBaseEntity(
    @Column(name = "CREATED_AT")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "UPDATED_AT")
    val updatedAt: LocalDateTime? = null
)