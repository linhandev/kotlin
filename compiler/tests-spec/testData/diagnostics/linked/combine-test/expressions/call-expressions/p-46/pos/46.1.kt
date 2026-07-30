// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 46 -> sentence 46
 *                type-inference, introduction-1 -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: member function on instantiated generic class inherits type argument
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T) {
    fun get(): T = v
}

fun case_1() {
    val b: Box<String> = Box("hello")
    val result = b.get()
    checkSubtype<String>(result)
}
