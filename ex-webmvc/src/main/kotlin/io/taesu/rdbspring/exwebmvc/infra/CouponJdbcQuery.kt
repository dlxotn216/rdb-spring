package io.taesu.rdbspring.exwebmvc.infra

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
@Component
@Transactional(readOnly = true)
class CouponJdbcQuery(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
): CouponQuery {
    override fun querySelectForAll(): List<CouponRow> {
        return jdbcTemplate.query(
            "select * from app_coupon",
            emptyMap<String, Any>()
        ) { rs, _ ->
            CouponRow(
                couponKey = rs long "coupon_key",
                name = rs string "name",
                description = rs stringNullable "description"
            )
        }
    }
}
