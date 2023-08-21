package com.example.home

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Suite::class)
@SuiteClasses(
    HomeViewModelTest::class,
    UserRepositoryImplTest::class,
    GetUserUseCaseTest::class,
    UserMapperTest::class
)
class MyTestSuite
