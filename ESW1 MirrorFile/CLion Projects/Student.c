#include "Student.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct Student {
	char firstName[25];
	char lastName[25];
	int studentID;
	char nationality[25];
}Student;


void printInfo(student_t self) {
	printf("%s, %s, %d, %s", self->firstName, self->lastName, self->studentID, self->nationality);
}
student_t* createStudent(char* firstName, char* lastName, int studentId, char* nationality) {
	Student* new_student = malloc(sizeof(Student));
	if (NULL == new_student) {
		return 3;
	}
	//strncpy(new_student->firstName,firstName, sizeof(new_student->firstName) - 1);
	//strncpy(new_student->lastName, lastName, sizeof(new_student->firstName) - 1);
	new_student->studentID = studentId;
	//strncpy(new_student->nationality, nationality, sizeof(new_student->firstName) - 1);
	return new_student;
}