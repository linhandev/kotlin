// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 14 -> sentence 14
 *                declarations, declarations-with-type-parameters -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: generic class property reference Box<Int>::v infers (Box<Int>) -> Int when type argument is specified, verifying type inference
 * HELPERS: checkType
 */

class Box<T>(val v: T)

// TESTCASE NUMBER: 1
fun case1() {
    val read: (Box<Int>) -> Int = Box<Int>::v
    checkSubtype<(Box<Int>) -> Int>(read)
}
