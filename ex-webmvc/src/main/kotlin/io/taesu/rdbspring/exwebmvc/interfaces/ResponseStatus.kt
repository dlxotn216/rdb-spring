package io.taesu.rdbspring.exwebmvc.interfaces

/**
 * Created by itaesu on 2024. 9. 12..
 *
 * @author Lee Tae Su
 * @version ex-webmvc
 * @since ex-webmvc
 */
enum class ResponseStatus(val value: String) {
    SUCCESS("success"),
    FAIL("fail")
    ;
}
