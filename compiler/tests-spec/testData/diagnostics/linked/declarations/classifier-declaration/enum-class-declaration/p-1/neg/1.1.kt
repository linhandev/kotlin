// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: enum class cannot inherit from class supertypes other than kotlin.Enum
 */

// TESTCASE NUMBER: 1
open class Base()

enum class E() : <!CLASS_IN_SUPERTYPE_FOR_ENUM!>Base<!>() {
    A,
    B
}
