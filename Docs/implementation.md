
# implementation

## current data structures

The first step was to define the data structure to represent numbers. The
underlying data structure is a linked list of digits. A digit contains informations
about the underlying number base. The current implementation supports numbers with
a number base of 2 up to 60.

The next data structure is a fraction type with an integer part, a numerator and a
denominator.

A vector data structure has been implemented. Some operations still have no implementation.

A matrix data structure has been implemented.

# future data structures

Introducing negative number bases might require a new implementation of a number.
Signs work differently with these.

A number with the base of 1 would not be a positional numeral system but a sequence of the same
symbol (unary numeral system).

A data structure for exponentiation expressions has been defined but requires an implementation.

A data structure for logarithm expressions has been defined but requires an implementation.

Various randomization operations have been defined (e.g. Die & Dice and random) an implementation.

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

+ division of numbers

+ factorial

+ conversion of a number to a number of a diffferent number base

When changing an implementation consider the dependencies to avoid endless loops.

The implementations of fractions, vectors, matrices depend on the implementations for numbers.

## future operations

+ various alternative implementations for multiplication

+ various alternative implementations for division with arbitrary precision

+ square root

+ exponentiation

+ logarithms

+ trigonometric operations

+ a data structure that combines a number with a unit of measurement (i.e. migrate/ integrate
  jmul subproject Measures)

+ a data structure for formulas (i.e. migrate/ integrate jmul subproject Formula)

+ implement/ test persistence of all number datat types (i.e. java serialization)

## important packages

+ jmul.math.digits
  Provides implementations for digits and numeral systems.

+ jmul.math.functions
  Contains implementations for various functions (e.g. monomial functions, polynomial functions, etc.)

+ jmul.math.fractions
  Provides implementations for fractions.

+ jmul.math.logarithms
  Contains implementations for logarithm expressions.

+ jmul.math.matrices
  Contains implementations for matrices.

+ jmul.math.numbers
  Contains implementations for numbers.

+ jmul.math.operations
  Contains generic operation definitions.

+ jmul.math.operations.implementation
  Contains implementations for various mathematical operations (e.g. addition, su7btraction, etc.).

+ jmul.math.operations.repository
  Contains the implementation for a repository which contains singletons of all operations.

+ jmul.math.signs
  Contains implementations for signs.

+ jmul.math.vectors
  Contains implementations for vecors.

