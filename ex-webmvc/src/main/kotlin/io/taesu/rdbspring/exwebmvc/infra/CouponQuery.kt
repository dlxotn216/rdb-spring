package io.taesu.rdbspring.exwebmvc.infra

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
interface CouponQuery {
    fun querySelectForAll(): List<CouponRow>
}
