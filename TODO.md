
# TODO

1) Implement feature to specify symbol set for a specific base. The current algorithm uses only
   digits and letters which limits the possible number bases.
   The symbol set needs to be translated to a regular expression. The number implementation
   uses a regular expression to parse number strings. Currently the regular expression is a
   constant.

2) Various arithmetic operations are not implemented.

3) More tests are needed.

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

13) Resolve conflict jmul.math.numbers.notation.Sign <-> jmul.math.signs.Sign
   Consolidate the class (retain jmul.math.signs.Sign and delete jmul.math.notation.Sign. Merge functionalities.

15) Check comments in test cases, including spelling errors.

16) Check while(true) { ... } constructs. Try to replace with a limited loop.

17) Implement various random fucntions (i.e. random number functions, dice, dice with non numerical symbols, etc.).

18) Consider adding an annotation so that each function implementation can be assigned an identifier. This
   should make the initialization eaiser (i.e. scanning classes and looking for classes with the annotation).

19) Some computations (e.g. division, rebasing, etc.) require arbitrarily cutting off the fraction part. The current
   mechanism uses a global variable in the class jmul.math.Math to determine a limit.
   Find a better way to cut and round a number with a fraction part.
   Alternative 1: throw an exception so the caller knows that something happened (i.e. result was truncated)
   Alternative 2: mark digits in order to identify groups of digits which are repeated

20) The test coverage needs improvement.

21) Do a static code review.
   The automation scripts have to be adapted for the changing in SonarQube.

22) Use a profiler to identify bottlenecks which can be optimized

23) Update wiki

24) Consolidate the division functions (i.e. diviso, modulo, division by subtraction, russion division, etc.).

25) Save hash codes in a member variable to minimize calculations. See number, fraction, vector, matrix.

26) Consider how compareTo might fit with vectors and matrices, i.e. what it could mean in each context.

27) Check that tests are annotated accordingly (e.g. @UnitTest, @ManualTest, etc.).

28) Add some concurrent tests.

29)


# Done

4) More variations of arithmetic operations which different signatures are needed (i.e.
   number and fraction paramaters).

7) Consolidate package structure. Remove superfluous packages.
	Reorganized the package structure.

9) Multiple classes of jmul.math.Constants cause a conflict.
	marked the class as deprecated
	the allowed number bases have to be moved to the positional numeral systems


# Obsolete

14) Consider changing the function singleton into maps.
    UnaryFunctionMap<Class, Function>, BinaryFunctionMap<Class, Map<Class, Function>>
	Null Checks of parameters would have to be moved out of the functions into the framework
