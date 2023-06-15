package com.mckernant1.sql

import org.ktorm.database.Database
import org.ktorm.dsl.forEach
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

fun main() {
    val db = Database.connect(
        "jdbc://postgresql://localhost:5432/testdb",
    )

    db.from(People)
        .select()
        .forEach {
            println(it[People.name])
        }

}


object People : Table<Nothing>("t_people") {
    val id = int("id").primaryKey()
    val name = varchar("name")
}
