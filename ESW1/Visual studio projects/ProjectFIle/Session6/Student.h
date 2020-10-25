#pragma once

typedef struct Student* student_t;
void printInfo(student_t self);
student_t* createStudent(char* firstName, char* lastName, int studentId, char* nationality);

