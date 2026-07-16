// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, runtime-available-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: star-projected classifier type is runtime-available for type checking
 * HELPERS: checkType
 */

interface Foo1511<A, B>
class Fee1511<T, U> : Foo1511<U, T>

// TESTCASE NUMBER: 1
fun case_1(foo: Foo1511<String, Int>) {
    checkSubtype<Boolean>(foo is Fee1511<*, *>)
}
