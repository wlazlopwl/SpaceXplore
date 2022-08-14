package com.appdevpwl.spacex.data.company

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: Company)

    @Transaction
    suspend fun replaceCompany(company: Company) {
        deleteCompany()
        insertCompany(company)
    }

    @Query("DELETE FROM company_table")
    suspend fun deleteCompany()

    @Query("SELECT * FROM company_table")
    fun getCompany(): LiveData<Company>

    @Query("SELECT COUNT(id) FROM company_table")
    fun getSize(): Int

}