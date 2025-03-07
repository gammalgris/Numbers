
# TODO

1) Implement feature to specify symbol set for a specific vbase. The current algorithm uses only
   digits and letters which limits the possible number bases.
   The symbol set needs to be translated to a regular expression. The number implementation
   uses a regular expression to parse number strings. Currently the regular expression is a
   constant.

2) Various arithmetic operations are not implemented.

3) More tests are needed.

4) More variations of arithmetic operations which different signatures are needed (i.e.
   number and fraction paramaters).

5) Consider allowing different algorithms for some arithmetic operations (e.g. division).

6) Review unit tests and implement the toString method with a test description.

8) Add a clean script for bash.

10) Replace expressions like "final Number ONE = new NumberImpl(operand.base(), "1");"
   Hard number creation with a hard coded symbol may cause errors later. Instead use a way by specifying
   the ordinal number instead of the symbol.

11) Check that the results are disjunct from input parameters (use test.jmul.math.numbers.NumberCheckHelper#checkNumbersAreUniqueInstances).
   Add this check to tests that don't have it.

12) Input parameters should by default not be modified unless marked as such.
   Add such a check to existing tests.

13) Resolve conflict jmul.math.notation.Sign <-> jmul.math.signs.Sign

14) Consider changing the function singleton into maps.
    UnaryFunctionMap<Class, Function>, BinaryFunctionMap<Class, Map<Class, Function>>
	Null Checks of parameters would have to be moved out of the functions into the framework

15) Check comments in test cases, including spelling errors.

16) Check while(true) { ... } constructs. Try to replace with a limited loop.

17)


# Done

7) Consolidate package structure. Remove superfluous packages.
	Reorganized the package structure.

9) Multiple classes of jmul.math.Constants cause a conflict.
	marked the class as deprecated
	the allowed number bases have to be moved to the positional numeral systems
