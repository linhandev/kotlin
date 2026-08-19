// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: only component1 cannot destructure two bindings
 */

// TESTCASE NUMBER: 1
class Box(val v: Int) {
    operator fun component1(): Int = v
}

fun case_1(b: Box): Int =
    b.let { <!COMPONENT_FUNCTION_MISSING!>(x, y)<!> -> x + y }
