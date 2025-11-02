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
