// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, function-declaration -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: local functions can be mutually recursive even when declared after forward calls type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun outer(n: Int): Int {
    fun isEven(x: Int): Boolean {
        fun isOdd(y: Int): Boolean = if (y == 0) false else isEven(y - 1)
        return if (x == 0) true else isOdd(x - 1)
    }
    return if (isEven(n)) 1 else 0
}

// TESTCASE NUMBER: 1
fun test(): Int = outer(4)

fun case1() {
    checkSubtype<Int>(test())
}
