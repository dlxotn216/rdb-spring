package io.taesu.rdbspring.exwebmvc.application

import io.taesu.rdbspring.exwebmvc.error.ApplicationException
import io.taesu.rdbspring.exwebmvc.error.ErrorCode
import io.taesu.rdbspring.exwebmvc.interfaces.FailResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.net.URI

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
@Component
class ExceptionResponseResolver {
    fun handleApplicationException(
        request: HttpServletRequest,
        e: ApplicationException,
    ): ResponseEntity<FailResponse> {
        val badRequest = HttpStatus.BAD_REQUEST
        val problemDetail = ProblemDetail.forStatus(badRequest).apply {
            type = URI.create("http://localhost:8080/errors/application-error/${e.errorCode}")
            title = "Bad Request"
            detail = e.message
            instance = URI.create(request.requestURI)
        }
        return ResponseEntity.badRequest().body(
            FailResponse(
                errorCode = e.errorCode,
                errorMessage = e.message ?: "Unknown Error",
                debugMessage = e.debugMessage,
                problemDetail = problemDetail,
            )
        )
    }

    fun handleEntityNotFoundException(
        request: HttpServletRequest,
        e: Exception,
    ): ResponseEntity<FailResponse> {
        val badRequest = HttpStatus.BAD_REQUEST
        val problemDetail = ProblemDetail.forStatus(badRequest).apply {
            type = URI.create("http://localhost:8080/errors/entity-not-found")
            title = "Bad Request (Entity Not Found)"
            detail = e.message
            instance = URI.create(request.requestURI)
        }
        return ResponseEntity.badRequest().body(
            FailResponse(
                errorCode = ErrorCode.NOT_FOUND,
                errorMessage = "존재하지 않는 데이터입니다.",
                debugMessage = e.message ?: "존재하지 않는 데이터입니다.",
                problemDetail = problemDetail,
            )
        )
    }

    fun handle(
        request: HttpServletRequest,
        e: Exception,
    ): ResponseEntity<FailResponse> {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST).apply {
            type = URI.create("http://localhost:8080/errors/unexpected-error")
            title = "Bad Request (Unexpected Error)"
            detail = e.message ?: "Unknown Error"
            instance = URI.create(request.requestURI)
        }
        return ResponseEntity.badRequest().body(
            FailResponse(
                errorCode = ErrorCode.UNHANDLED_EXCEPTION,
                errorMessage = "예기치 못한 오류가 발생했습니다. 관리자에게 연락해주세요.",
                debugMessage = e.message ?: "예기치 못한 오류입니다. ($e)",
                problemDetail = problemDetail,
            )
        )
    }
}
