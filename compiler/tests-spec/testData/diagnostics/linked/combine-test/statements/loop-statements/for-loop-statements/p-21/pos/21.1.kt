// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                declarations, function-declaration, extension-function-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: extension operator fun iterator() makes third-party type iterable type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box(val data: List<Int>)

// TESTCASE NUMBER: 1
operator fun Box.iterator(): Iterator<Int> = data.iterator()

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in Box(listOf(4, 5))) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
