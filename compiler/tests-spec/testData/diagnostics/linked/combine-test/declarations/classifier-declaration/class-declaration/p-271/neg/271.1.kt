// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 271 -> sentence 271
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 271 -> sentence 271
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 271 -> sentence 271
 * NUMBER: 1
 * DESCRIPTION: private nested classes are invisible outside the declaring outer class (INVISIBLE_MEMBER/INVISIBLE_REFERENCE); covers constructor call, type reference, and qualified name from another top-level class; contrasts with p-263 top-level private file scope, with next-point Outer-internal success, and with nested-and-inner-classifiers p-1 public Nested
 */

// TESTCASE NUMBER: 1
class TokenOuter {
    private class Nested(val id: Int = 1)
}

fun case1() {
    TokenOuter.<!INVISIBLE_MEMBER!>Nested<!>()
}

// TESTCASE NUMBER: 2
class CodeOuter {
    private class Nested
}

fun case2() {
    val x: CodeOuter.<!INVISIBLE_REFERENCE!>Nested<!> = CodeOuter.<!INVISIBLE_MEMBER!>Nested<!>()
}

// TESTCASE NUMBER: 3
class LabelOuter {
    private class Nested(val label: String)
}

class LabelClient {
    fun make() {
        LabelOuter.<!INVISIBLE_MEMBER!>Nested<!>("x")
    }
}
