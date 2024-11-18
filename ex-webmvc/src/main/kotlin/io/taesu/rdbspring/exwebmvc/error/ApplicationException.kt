package io.taesu.rdbspring.exwebmvc.error

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
class ApplicationException(
    val errorCode: ErrorCode,
    message: String,
    val debugMessage: String = message,
    cause: Throwable? = null,
): RuntimeException(message, cause)
