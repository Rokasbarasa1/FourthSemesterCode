cmake_minimum_required(VERSION 3.17)
project(CLion_Projects C)

set(CMAKE_C_STANDARD 99)

add_executable(CLion_Projects main.c list.c list.h Student.c Student.h StudentHandler.c StudentHandler.h StudentList.c StudentList.h)