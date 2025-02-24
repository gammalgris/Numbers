
# implementation

## current data structures

The first step was to define the data structure to represent numbers. The
underlying data structure is a linked list of digits. A digit contains informations
about the underlying number base. The current implementation supports numbers with
a number base of 2 up to 60.

The next data structure is a fraction type with an integer part, a numerator and a
denominator.


# future data structures

Introducing negative number bases might require a new implementation of a number.
Signs work differently with these.

A number with the base of 1 would not be a positional numeral system but a set of
any number of the same symbol (unary numeral system).

A vector data structure will be needed.

A matrix data structure will be needed.


## dependencies of operations

Operations have been implemented according to following order:

+ add and subtract digits (i.e. required a digit type)

+ absolute value of a number

+ addition of numbers (i.e. required a signed number type)

+ complement of a number

+ subtraction of numbers

+ comparison operations (i.e. <, <=, =, >, >=)

+ increment and decrement numbers

+ truncating the fraction

+ halving and doubling numbers

+ multiplication of numbers

+ division of numbers (i.e. required a signed fraction type)

When changing an implementation consider the dependencies to avoid endless loops.


## future operations

+ converting numbers to a different number base

+ various alternative implementations for multiplication

+ various alternative implementations for division with arbitrary precision

+ square root

+ exponentiation

+ factorial

+ logarithms

+ trigonometric operations

+ vector operations

+ matrix operations


## important packages

+ jmul.math.digits
  Provides implementations for digits and numeral systems.

+ jmul.math.fractions
  Provides implementations for fractions.
  
+ jmul.math.functions.implementation
  Contains all function implementations

+ jmul.math.functions.repository
  Contaisn the implementation for the function repository.

+ jmul.math.matrices
  Contains implementations for matrices.

+ jmul.math.numbers
  Contains implementations for numbers.

+ jmul.math.operations
  Contains operation definitions.

+ jmul.math.signs
  Contains implementations for signs.

+ jmul.math.vectors
  Contains implementations for vecors.

