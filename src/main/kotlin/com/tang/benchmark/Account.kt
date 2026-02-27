package com.tang.benchmark

import com.baomidou.mybatisplus.annotation.TableId
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.tang.kite.annotation.id.IdType
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Tang
 */
class Account(

    @Id(keyType = KeyType.Auto)
    @com.tang.kite.annotation.id.Id(type = IdType.AUTO)
    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
    var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var createTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null,
    var balance: BigDecimal? = null

)
