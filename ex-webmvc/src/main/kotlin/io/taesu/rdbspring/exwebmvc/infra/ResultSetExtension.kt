package io.taesu.rdbspring.exwebmvc.infra

import java.sql.ResultSet

/**
 * Created by itaesu on 2024/06/17.
 *
 * @author Lee Tae Su
 * @version rdb-spring
 * @since rdb-spring
 */

infix fun ResultSet.long(columnName: String): Long {
    return this.getLong(columnName)
}

infix fun ResultSet.longNullable(columnName: String): Long? {
    val value = this.getLong(columnName)
    return when (wasNull()) {
        true -> null
        false -> value
    }
}

infix fun ResultSet.string(columnName: String): String {
    return this.getString(columnName)
}

infix fun ResultSet.stringNullable(columnName: String): String? {
    val value = this.getString(columnName)
    return when (wasNull()) {
        true -> null
        false -> value
    }
}
