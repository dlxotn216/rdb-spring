package io.taesu.rdbspring.exwebmvc.interfaces

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonView
import io.taesu.rdbspring.exwebmvc.application.ExceptionResponseResolver
import io.taesu.rdbspring.exwebmvc.error.ApplicationException
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * LH-?xINLjc#Y7(*7nq
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
@ConditionalOnProperty(
    value =["app.error.public"],
    matchIfMissing = true,
    havingValue = "true",
)
@RestControllerAdvice
class ExceptionHandlerAdvice(
    private val resolver: ExceptionResponseResolver,
) {
    @JsonView(FailResponse.PublicView::class)
    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(
        request: HttpServletRequest,
        e: ApplicationException,
    ): ResponseEntity<FailResponse> {
        return resolver.handleApplicationException(request, e)
    }

    @JsonView(FailResponse.PublicView::class)
    @ExceptionHandler(EntityNotFoundException::class, JpaObjectRetrievalFailureException::class)
    fun handleEntityNotFoundException(
        request: HttpServletRequest,
        e: Exception,
    ): ResponseEntity<FailResponse> {
        return resolver.handleEntityNotFoundException(request, e)
    }

    @JsonView(FailResponse.PublicView::class)
    @ExceptionHandler(Exception::class)
    fun handleException(
        request: HttpServletRequest,
        e: Exception,
    ): ResponseEntity<FailResponse> {
        return resolver.handle(request, e)
    }
}

@ConditionalOnProperty(
    value =["app.error.public"],
    havingValue = "false",
)
@RestControllerAdvice
class InternalExceptionHandlerAdvice(
    private val resolver: ExceptionResponseResolver,
): ExceptionHandlerAdvice(resolver) {
    @JsonView(FailResponse.InternalView::class)
    @ExceptionHandler(ApplicationException::class)
    override fun handleApplicationException(
        request: HttpServletRequest,
        e: ApplicationException,
    ): ResponseEntity<FailResponse> {
        return resolver.handleApplicationException(request, e)
    }

    @JsonView(FailResponse.InternalView::class)
    @ExceptionHandler(EntityNotFoundException::class, JpaObjectRetrievalFailureException::class)
    override fun handleEntityNotFoundException(
        request: HttpServletRequest,
        e: Exception,
    ): ResponseEntity<FailResponse> {
        return resolver.handleEntityNotFoundException(request, e)
    }

    @JsonView(FailResponse.InternalView::class)
    @ExceptionHandler(Exception::class)
    override fun handleException(
        request: HttpServletRequest,
        e: Exception,
    ): ResponseEntity<FailResponse> {
        return resolver.handle(request, e)
    }
}

@JvmInline
value class CarNumber private constructor(val value: String) {
    init {
        require(value.length == 7) { "Car number must be 7 characters" }
    }

    companion object {
        @JsonCreator
        operator fun invoke(value: String) = CarNumber(value.trim())
    }
}

fun main() {
    val carNumber = CarNumber("123456")
    println(carNumber)

    val carNumber2: CarNumber? = CarNumber("1234567")
    requireNotNull(carNumber2) { "carNumber is Required." }
    // elvis operator 대신 requireNotNull을 사용하여 null 체크 할수도 있음
    carNumber2.value
}
