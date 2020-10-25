cmake_minimum_required(VERSION 3.17)
project(Exercises C)

set(CMAKE_C_STANDARD 99)

add_executable(Exercises main.c Student.c Student.h List.c List.h StudentHandler.c StudentList.c StudentList.h StudentHandler.h)