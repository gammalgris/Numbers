
# TODO

1) Implement feature to specify symbol sets for a specific number base. The current algorithm uses
   only digits and letters which limits the possible number bases.
   The symbol set needs to be translated to a regular expression. The number implementation
   uses a regular expression to parse number strings. Currently the regular expression is a
   constant.

3) More tests are needed. There are still quite big test gaps.

6) Review unit tests and implement the toString method with a test description.

8) Add a clean script for bash.

10) Replace expressions like "final Number ONE = new NumberImpl(operand.base(), "1");"
    number creation with a hard coded string may cause errors later, especially if the symbol set can be changed.
    Instead use a way by specifying the ordinal number instead of the string/ symbol.

11) Check that the results are disjunct from input parameters (use test.jmul.math.numbers.NumberCheckHelper#checkNumbersAreUniqueInstances).
    Add this check to tests that don't have it.
    -> Rationale: When using a public operation the input number should never be modified and the working instances and result
                  should be unique copies of the input number or unique number instances.
                  This is to avoid unwanted side effects.
    Seperate the tests for correctness and the tests for checking if the object instances of input parameters and
	are not the same (i.e. results have always to be unique instances).

12) Input parameters should by default not be modified unless marked as such.
    Add such a check to existing tests.
    -> Note: Similar to 11)

13) Resolve conflict jmul.math.numbers.notation.Sign <-> jmul.math.signs.Sign
    Consolidate the class (retain jmul.math.signs.Sign and delete jmul.math.notation.Sign. Merge functionalities.
	See 37)

15) Check comments in test cases, including spelling errors.

16) Check while(true) { ... } constructs. Try to replace with a limited loop.
    -> Rationale: bugs in implementations and exit conditions might result in endless loops. On the other hand
                  exiting prematurely might result in a wrong result.

17) Implement various random fucntions (i.e. random number functions, dice, dice with non numerical symbols, etc.).

18) Consider adding an annotation so that each function implementation can be assigned an identifier.
    -> Rationale: This should make the initialization easier (but results i.e. in scanning classes and looking for
       classes with the annotation).

20) The test coverage needs improvement.

21) Do a static code review.
    The automation scripts have to be adapted for the changes in SonarQube. The sonar ant runner is probably not
	maintained. See how the command line tool can use test result and coverage informations.
	Create a bash and batch script to automate the whole build process.

22) Use a profiler to identify bottlenecks which can be optimized

23) Update wiki/ documentation (i.e. needs more examples)

24) Consolidate the division functions (i.e. diviso, modulo, division by subtraction, russion division, etc.).
    Add more tests. There might be issues with the current implementation with odd number bases.
	Remove obsolete function implementations. Consolidate the function identifiers.

25) Save hash codes in a member variable to minimize calculations. See number, fraction, vector, matrix.

26) Consider how compareTo might fit with vectors and matrices, i.e. what it could mean in each context.

27) Check that tests are annotated accordingly (e.g. @UnitTest, @ManualTest, etc.).

28) Add some concurrent tests.

29) repeating digit groups in the fraction part need some kind of marker or identification.

30) Implement a class for numbers with negative bases. The skeleton has been generated.

31) Implement an operation to check if a number or fraction is within a specified interval
	add a default constant for epsilon (i.e. a small number)

32) Experiment with surreal numbers (see https://en.wikipedia.org/wiki/Surreal_number)

33) Clarify in the documentation which numbers can be represented (i.e. Natural Numbers, Integers,
    Rational Numbers, Real Numbers), approximatated (i.e. some Rational Numbers, Real Numbers) and
	which cannot be represented yet (i.e. complex numbers).

35) Consider adding a stream constructor for large numbers (i.e. stream of digits/ characters).

36) Add methods to convert numbers back to primitive types (i.e. int, long, double, etc.) and their
    respective erapper classes (i.e. Integer, Double, etc.)

39) Various arithmetic operations need alternative algorithms in order to check the correctness of
    individual algorithms.

40) Change the function "Fraction divide(Number)" to "Number divide(Number)" and
    "Fraction divide(Number, Number)" to "Number divide(Number, Number)"
    Add "Fraction Math.toFraction(Number)", "Fraction Math.toFraction(Number,Number)" and
	"Fraction Math.toFraction(Number,Number,Number)". See also 24.

41) The function "Number Fraction.evaluate()" requires an aditional signature.
    "Number Fraction.evaluate(FunctionIdentifier)" to chose a among available division algorithms.

42) Need speed comparisons between division algorithms.

43) The conversion functions need rework. They are missing in the jmul.math.Math class.

44) Implement optimizations (e.g. right end of the linked list, left end of the linked list) to
    minimize traversing the linked list.

45) Need to rework constructors of Fraction/ MixedFraction. Update tests.

46)


# Done

2) Various arithmetic operations are not implemented.

4) More variations of arithmetic operations which different signatures are needed (i.e.
   number and fraction paramaters).
   -> Done (see the added basic operations for fractions)

5) Consider allowing different algorithms for some arithmetic operations (e.g. division).
   -> Solution: a mechanism exists to add and specify valid algorithms for an operation (see division and rounding)
   -> Application: apply that mechanism where needed

7) Consolidate package structure. Remove superfluous packages.
   -> Solution: reorganized the package structure.

9) Multiple classes of jmul.math.Constants cause a conflict.
   marked the class as deprecated
   the allowed number bases have to be moved to the positional numeral systems
   -> Solution: in the project Numbers the class jmul.math.Constants has been deleted. Constants have been moved to
                other classes

19) Some computations (e.g. division, rebasing, etc.) require arbitrarily cutting off the fraction part. The current
    mechanism uses a global variable in the class jmul.math.Math to determine a limit.
    Find a better way to cut and round a number with a fraction part.
    Alternative 1: throw an exception so the caller knows that something happened (i.e. result was truncated)
    Alternative 2: mark digits in order to identify groups of digits which are repeated
    -> Solution: there remains a constant for a default precision (i.e. the variable has been turned into a constant).
    -> Solution: some operations now have an additional variant where you specify a precision

34) Consider changing the operation Fraction divide(Number,Number) to Number divide(Number,Number).
    There now exists an alternative to the first operation: Fraction Number.toFraction()
    -> resolved by renaming the Fraction variant and adding new methods with different parameter signatures.

37) Rework constructors and helper classes (i.e. createNumber methods). Move the logic to parse input
    parameters out of the constructors into the helper class. The helper class will depending on the
	number base instantiate a different number implementation.
    -> Cleaned up constructors in NumberImpl. Remaining Constructors are now protected. Moved the parsing
	   into NumberHelper.

38) After the constructors are reworked, update tests.
    -> Updated tests.


# Obsolete

14) Consider changing the function singleton into maps.
    UnaryFunctionMap<Class, Function>, BinaryFunctionMap<Class, Map<Class, Function>>
	Null Checks of parameters would have to be moved out of the functions into the framework
    -> Solution: this TODO is now obsolete
