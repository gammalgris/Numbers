
# Overview

The project goal is to create a math library with a custom number type. The
main motivation is to refresh math skills.

The central component of this library is a custom number type which is similar
to a string but consists of a linked list of digits.

In order to avoid duplicate implementations of functions, functions are stored
as objects and are used when a calculation needs to be done.

Implemented requirements:

* A custom number implementation with higher precision and which represents
  both integers and floating point numbers.
* The current algorithm for determining digit sets is resticted to digits,
  lower case letters and upper case letters.
* Working with numbers with different number bases (i.e. base 2 and upwards).
* Implemented arithmetic operations: addition, subtraction, multiplication,
  division (simple division with a fraction type result)
* A custom fraction type.

Future Requirements:

* Specifying custom symbol sets for digits of a specific number base.
* Negative number bases
* Arithmetic operations: division (various algorithms), exponentiation
* Logarithmic operations
* Trigonimetric operations
* vector arithmetic
* matrix arithmetic
* formula parsing (see jmul library submodule formula)
* a composite type of a number with a measurement unit (see jmul library
  submodule measurement units)
