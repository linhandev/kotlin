// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 12 -> sentence 12
 *                expressions, call-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: local helper function declared inside member function body type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C {
    fun compute(x: Int): Int {
        fun double(v: Int): Int = v * 2
        return double(x)
    }
}

// TESTCASE NUMBER: 1
fun test(): Int = C().compute(4)

fun case1() {
    checkSubtype<Int>(test())
}
