package io.taesu.rdbspring.exwebmvc.error

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
fun throwResourceNotFound(message: String): Nothing = throw ApplicationException(
    ErrorCode.NOT_FOUND,
    message
)
