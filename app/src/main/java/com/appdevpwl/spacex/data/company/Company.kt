package com.appdevpwl.spacex.data.company

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.converters.CompanyLinksConverter
import com.appdevpwl.spacex.data.converters.HeadquartersConverter

@Entity(tableName = "company_table")
data class Company(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids: Int,
    val ceo: String?,
    val coo: String?,
    val cto: String?,
    val cto_propulsion: String?,
    val employees: Int?,
    val founded: Int?,
    val founder: String?,
    @TypeConverters(HeadquartersConverter::class)
    val headquarters: Headquarters?,
    val id: String?,
    val launch_sites: Int?,
    @TypeConverters(CompanyLinksConverter::class)
    val links: Links?,
    val name: String?,
    val summary: String?,
    val test_sites: Int?,
    val valuation: Long?,
    val vehicles: Int?,
)