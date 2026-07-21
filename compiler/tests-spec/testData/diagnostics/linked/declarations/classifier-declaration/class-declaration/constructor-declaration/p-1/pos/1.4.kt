// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: primary constructor with val and var property parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Foo(val readOnly: Int, var mutable: String)

fun case1(f: Foo) {
    f.readOnly checkType { check<Int>() }
    f.mutable checkType { check<String>() }
}
