package io.taesu.rdbspring.exwebmvc.domain

import jakarta.persistence.*
import org.hibernate.annotations.NaturalId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */
@Entity
@Table(name = "app_coupon")
class Coupon(
    @NaturalId
    @Column(name = "couponId", nullable = false, unique = true, updatable = false)
    val couponId: UUID = UUID.randomUUID(),

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = true)
    var description: String?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_key")
    val couponKey: Long = 0L,
): BaseEntity<Long>() {
    override fun getId(): Long = couponKey
}

interface CouponRepository: JpaRepository<Coupon, Long>
