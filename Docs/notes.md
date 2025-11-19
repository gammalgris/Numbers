# Questions / Notes

1) Regarding different number bases - Integers can be translated without loss. Fraction parts seem to incur losses/
   rounding errors when translated. It's not clear how much rounding errors contribute to the loss. It appears that
   there is also a general loss due to lower precision.
   The lower the number base (1 < base 1 < base 2) the lower the precision of the fraction part? If so then how to
   demonstrate the loss and determine how rounding losses contribute to this? Need some examples.

2) It seems that a higher number base might reduce the number of computations for calculations. Numbers in lower
   number bases have more digits compared to the same number in a higher number base (the total number of
   digits, i.e. integer part and fractional part). More digits require more computing cycles and thus more
   computations.
   How to quantify the difference in computations? How significant can this difference be?

3) Consider an interval - Can you pack more distinct numbers (i.e. different states) into an interval on higher
   number bases?
   Take a number in number base 2 with 10 digits. Take a number in base 10 with 10 digits (or higher). Count/
   quantify the difference in posible numbers (i.e. states).

4) Due to the dependencies recursion may be problematic because the default stack size may be exhausted quickly. In
   such a case avoid recursion and use ordinary loops (i.e. for or while loops). The math utilities should work with
   default memory settings.

5) Some operations (e.g. addition, subtraction, multiplication, halving, doubling, incrementing, decrementing, etc.)
   will not round the result. This is on purpose. If rounding is necessary then it should be done explicitely before
   and/ or after the calculation. Adding rounding by default costs performance.

6) There is a dependency and hierarchy between various ata types:
   a) digit (there is a distinct digit set for each number base used at runtime)
   b) number (i.e. a linked list of digits)
   c) fraction (the components of a fraction depend on the number implementations)
   d) vector (the components of a vector depend on the number implementations)
   e) matrix (the components of a matrix depend on the number implementations)
   f) function (the coefficients of a function depend on the number implementations)

7) A polynomial function can be translated into a number and back.

8) There is a dependency and hierarchy between operations:
   a) addition of numbers (depends on addition of digits)
   b) subtraction of numbers (depends on complement of a number and addition)
   c) multiplication (depends on addition, see details in the various multiplication implementations for detailed
      dependencies)
   d) division (see details in the various multiplication implementations for detailed dependencies)
   e) root/ nth-root (depends on the basic four operations)
   f) exponentiation (depends on the basic four operations)
   g) sine (depends on the basic four operations and calculating roots and exponentiation)
   h) cosine (depends on the basic four operations and calculating roots and exponentiation)
