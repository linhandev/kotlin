// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 165 -> sentence 165
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 165 -> sentence 165
 *                inheritance, inheriting -> paragraph 165 -> sentence 165
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 165 -> sentence 165
 * NUMBER: 1
 * DESCRIPTION: enum classes are implicitly final and their constructors are inaccessible to subclass constructor delegation in class declaration
 */

// TESTCASE NUMBER: 1
enum class Status {
    READY
}

class BadStatus : <!FINAL_SUPERTYPE, INVISIBLE_MEMBER!>Status<!>()

// TESTCASE NUMBER: 2
enum class Code(val value: Int) {
    ONE(1)
}

class BadCode(value: Int) : <!FINAL_SUPERTYPE, INVISIBLE_MEMBER!>Code<!>(value)

// TESTCASE NUMBER: 3
interface Marker

enum class Mode(val label: String) {
    A("a")
}

class MixedMode(label: String) : <!FINAL_SUPERTYPE, INVISIBLE_MEMBER!>Mode<!>(label), Marker
