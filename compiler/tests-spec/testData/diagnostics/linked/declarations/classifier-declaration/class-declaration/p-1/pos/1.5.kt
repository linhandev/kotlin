// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: class declaration without supertype implicitly extends kotlin.Any
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Foo

fun case1(x: Foo) {
    checkSubtype<Any>(x)
    val z: Any = Foo()
}
