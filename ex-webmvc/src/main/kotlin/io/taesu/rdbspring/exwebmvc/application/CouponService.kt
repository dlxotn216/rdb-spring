package io.taesu.rdbspring.exwebmvc.application

import io.taesu.rdbspring.exwebmvc.domain.Coupon
import io.taesu.rdbspring.exwebmvc.domain.CouponRepository
import io.taesu.rdbspring.exwebmvc.infra.CouponQuery
import io.taesu.rdbspring.exwebmvc.infra.CouponRow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val couponQuery: CouponQuery,
) {
    fun create(): Coupon = couponRepository.save(
        Coupon(
            name = "Coupon",
            description = "Coupon Description"
        )
    )

    fun retrieve(couponKey: Long): Coupon = couponRepository.getReferenceById(couponKey)

    // replica db
    fun retrieveAll(): List<CouponRow> = couponQuery.querySelectForAll()

    // master db
    @Transactional
    fun retrieveAllWithTransaction(): List<CouponRow> = couponQuery.querySelectForAll()
}
