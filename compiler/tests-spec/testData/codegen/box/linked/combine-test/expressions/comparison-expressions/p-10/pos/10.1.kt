/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Double == uses equals (0.0 == -0.0) unlike compareTo ordering of signed zeros
 */

// TESTCASE NUMBER: 1
fun equalsZeroes(): Boolean = 0.0 == -0.0

fun compareToZeroes(): Int = 0.0.compareTo(-0.0)

fun box(): String {
    if (!equalsZeroes()) return "NOK: equals"
    // IEEE / JVM: 0.0 compares greater than -0.0
    if (compareToZeroes() != 1) return "NOK: compareTo"
    if (equalsZeroes() && compareToZeroes() == 0) return "NOK: conflated"
    return "OK"
}
