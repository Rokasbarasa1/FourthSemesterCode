//
// Created by Rokas on 24/10/2020.
//

#ifndef EXERCISES_STUDENT_H
#define EXERCISES_STUDENT_H

#include <stdint.h>

typedef struct Student* student_t;
student_t* student_createStudent(char* firstName, char* lastName, uint16_t studentId, char* nationality);
void student_printInfo(student_t self);
uint16_t student_getId(student_t self);
char* student_getLastName(student_t self);

#endif //EXERCISES_STUDENT_H
