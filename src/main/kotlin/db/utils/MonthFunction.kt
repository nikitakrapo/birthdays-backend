package com.nikitakrapo.db.utils

import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.CustomFunction
import org.jetbrains.exposed.v1.core.CustomStringFunction
import org.jetbrains.exposed.v1.core.IntegerColumnType

fun extractMonth(column: Column<*>): CustomFunction<Int> {
    return CustomFunction(
        functionName = "EXTRACT",
        columnType = IntegerColumnType(),
        CustomStringFunction("MONTH FROM", column)
    )
}

fun extractDay(column: Column<*>): CustomFunction<Int> {
    return CustomFunction(
        functionName = "EXTRACT",
        columnType = IntegerColumnType(),
        CustomStringFunction("DAY FROM", column)
    )
}
