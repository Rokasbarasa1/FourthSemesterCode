cmake_minimum_required(VERSION 3.17)
project(Session6_4 C)

set(CMAKE_C_STANDARD 99)

add_executable(Session6_4 main.c HealthHandler.c HealthHandler.h Room.c Room.h Humidity.c Humidity.h Temperature.c Temperature.h Support/List.c Support/List.h Drivers/TempDriver.c Drivers/TempDriver.h Drivers/HumidityDriver.c Drivers/HumidityDriver.h)