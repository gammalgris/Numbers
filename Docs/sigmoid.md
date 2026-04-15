
# linear approximation of a sigmoid function

The purpose is to reduce calculations by not using costly exponentiation and the number e.
Split the function into several intervals and provide a linear approximation for each interval.
The approximation has a certain error.

sigmoid function:
f(x) = 1 / (1 + e^(-x)) for -5 < x < 5


approximation:

f(-4) = 0
f(-2) = 0,1
f(-1) = 0,25
f(0)  = 0,5
f(1)  = 0,75
f(2)  = 0,9
f(4)  = 1


[-1;1]
	f(x) = a * x + b
	
	a = 1/4
	b = 1/2

	f(x) = 1/4 * x + 1/2

	1/4 * -1 + 1/2 = 1/4
	1/4 * 0 + 1/2 = 1/2
	1/4 * 1 + 1/2 = 3/4

[-2;-1]
	f(x) = a * x + b

	a = 0,15
	b = 0,4

	f(x) = 3/20 * x + 2/5

	3/20 * -2 + 2/5 = -6/20 + 2/5 = -6/20 + 8/20 = 2/20 = 1/10
	3/20 * -1 + 2/5 = -3/20 + 2/5 = -3/20 + 8/20 = 5/20 = 1/4
	3/20 * 0 + 2/5 = 2/5

[-4;-2]
	f(x) = a * x + b

	a = ?
	b = 0,2

	a * x + 1/5

	a * -4 + 1/5 = 0 -> -4a = -1/5 -> a = -1/5 * -5/20 -> a = 1/20
	a * -2 + 1/5 = 1/10 -> -2a = 1/10 - 1/5 -> -2a = -1/10 -> a = -1/10 * -10/20 -> a = 1/20
	a * -0 + 1/5 = 1/5

	a = 0,05
	b = 0,2

	f(x) = 1/20 * x + 1/5

[-infinity;-4]
	f(x) = 0

[1;2]
	f(x) = a * x + b

	a = ?
	b = ?

	a * 0 + b = 0,6
	a * 1 + b = 0,75
	a * 2 + b = 0,9

	a = ?
	b = 0,6

	a * 0 + 3/5 = 3/5
	a * 1 + 3/5 = 3/4 -> a = 3/4 - 3/5 -> a = 3/20
	a * 2 + 3/5 = 9/10 -> 2a = 9/10 - 3/5 -> 2a = 3/10 -> a = 3/10 * 1/2 -> a = 3/20

	a = 0,15
	b = 0,6

	f(x) = 3/20 * x + 3/5

[2;4]
	f(x) = a * x + b

	a = ?
	b = ?

	a * 0 + b = 0,8
	a * 2 + b = 0,9
	a * 4 + b = 1

	a = ?
	b = 0,8

	a * 0 + 4/5 = 4/5
	a * 2 + 4/5 = 9/10 -> 2a = 1/10 -> a = 1/10 * 1/2 -> a = 1/20
	a * 4 + 4/5 = 1 -> 4a = 1/5 -> a = 1/5 * 1/4 -> a = 1/20

	a = 0,05
	b = 0,8

	f(x) = 1/20 * x + 4/5

[4;infinity]
	f(x) = 1


f(x) :
	x < -4 : g^1(x) = 0
	[-4, -2) : g^2(x) = 1/20 * x + 1/5
	[-2, -1) : g^3(x) =  3/20 * x + 2/5
	[-1, 1] : g^4(x) = 1/4 * x + 1/2
	(1, 2] : g^5(x) = 3/20 * x + 3/5
	(2, 4] : g^6(x) = 1/20 * x + 4/5
	x > 4 : g^7(x) = 1
