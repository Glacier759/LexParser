/*
 * =====================================================================================
 *
 *       Filename:  hello.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2015年04月21日 13时32分36秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Glacier (RenLixiang), OurHom.759@gmail.com
 *        Company:  Class 1204 of Computer Science and Technology
 *
 * =====================================================================================
 */

#include <unistd.h>
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "test.h"

int main(int argc, char *argv[])	//我再试试看这种的OK不？
{//如果是这样呢？
	printf("hello world!\n");
	int index = 0;
	for ( index = 1; index < 10; index ++ ) {
		if ( index % 2 == 0 ) {
			index += 3;
		}
		else {
			break;
		}
	}

	return EXIT_SUCCESS;
}

