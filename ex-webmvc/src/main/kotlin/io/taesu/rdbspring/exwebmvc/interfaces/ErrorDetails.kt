package io.taesu.rdbspring.exwebmvc.interfaces

import com.fasterxml.jackson.annotation.JsonView
import io.taesu.rdbspring.exwebmvc.error.ErrorCode
import io.taesu.rdbspring.exwebmvc.interfaces.FailResponse.InternalView
import org.springframework.http.ProblemDetail

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
class ErrorDetails(
    val errorCode: ErrorCode,
    @field:JsonView(InternalView::class)
    val debugMessage: String,
    @field:JsonView(InternalView::class)
    val problemDetail: ProblemDetail,
)
