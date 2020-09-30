package com.appdevpwl.spacex.data

import javax.inject.Inject

class TestEntityRepository @Inject constructor(private val testEntityDao: TestEntityDao) {

    fun allTest():List<TestEntity> {

        return testEntityDao.getAllTest()

    }

    fun insert(testEntity: TestEntity) {

        testEntityDao.insert(testEntity)

    }
}