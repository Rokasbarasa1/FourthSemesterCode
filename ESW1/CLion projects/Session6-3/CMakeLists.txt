cmake_minimum_required(VERSION 3.17)
project(Session6_3 C)

set(CMAKE_C_STANDARD 99)

add_executable(Session6_3 main.c Room.c Room.h HealthHandler.c HealthHandler.h Temperature.c Temperature.h Humidity.c Humidity.h)