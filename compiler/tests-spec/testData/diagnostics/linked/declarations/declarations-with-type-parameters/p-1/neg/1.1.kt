// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: object and enum cannot declare type parameters; mutually exclusive where bounds on one parameter are rejected
 */

// TESTCASE NUMBER: 1
object GenericObject<!TYPE_PARAMETERS_IN_OBJECT!><T><!>

// TESTCASE NUMBER: 2
enum class GenericEnum<!TYPE_PARAMETERS_IN_ENUM!><T><!> {
    A
}

// TESTCASE NUMBER: 3
interface BadBounds<K, V, W> where W : K, W : <!BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER!>V<!>
