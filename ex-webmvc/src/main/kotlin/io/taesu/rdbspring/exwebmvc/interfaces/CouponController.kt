package io.taesu.rdbspring.exwebmvc.interfaces

import io.taesu.rdbspring.exwebmvc.application.CouponService
import io.taesu.rdbspring.exwebmvc.domain.Coupon
import io.taesu.rdbspring.exwebmvc.infra.CouponRow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
@RestController
class CouponController(private val couponService: CouponService) {
    @PostMapping("/api/v1/coupons")
    fun create(): Coupon = couponService.create()

    @GetMapping("/api/v1/coupons/{couponKey}")
    fun retrieve(@PathVariable couponKey: Long): Coupon = couponService.retrieve(couponKey)

    @GetMapping("/api/v1/coupons")
    fun retrieveAll(): List<CouponRow> = couponService.retrieveAll()

    @GetMapping("/api/v2/coupons")
    fun retrieveAllV2(): List<CouponRow> = couponService.retrieveAllWithTransaction()
}
