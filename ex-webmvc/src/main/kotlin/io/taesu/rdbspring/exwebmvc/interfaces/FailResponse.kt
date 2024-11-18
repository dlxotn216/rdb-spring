package io.taesu.rdbspring.exwebmvc.interfaces

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import io.taesu.rdbspring.exwebmvc.error.ErrorCode
import org.springframework.http.ProblemDetail

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
class FailResponse(
    errorCode: ErrorCode,
    errorMessage: String,
    debugMessage: String = errorMessage,
    private val additional: Map<String, Any> = emptyMap(),
    problemDetail: ProblemDetail,
): Response(
    status = ResponseStatus.FAIL,
    message = errorMessage,
) {
    val error = ErrorDetails(errorCode, debugMessage, problemDetail)

    open class PublicView
    open class InternalView
}
