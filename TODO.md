
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

7) Consolidate package structure. Remove superfluous packages.

8) Add a clean script for bash.
