cmake_minimum_required(VERSION 3.6)
project(pa5)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES Dictionary.c Dictionary.h main.c)
add_executable(pa5 ${SOURCE_FILES})