cmake_minimum_required(VERSION 3.6)
project(pa5)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    Dictionary.c
    Dictionary.h
    DictionaryTest.c
    )

add_executable(pa5 ${SOURCE_FILES})