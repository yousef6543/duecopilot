package com.duepilot.data

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Date

class Converters {
    @TypeConverter fun bigDecimalToString(v: BigDecimal?): String? = v?.toPlainString()
    @TypeConverter fun stringToBigDecimal(s: String?): BigDecimal? = s?.let { BigDecimal(it) }
}
