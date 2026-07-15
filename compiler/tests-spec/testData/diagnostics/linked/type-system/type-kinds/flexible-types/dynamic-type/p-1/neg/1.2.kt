// FIR_IDENTICAL
// DIAGNOSTICS: -UNSUPPORTED -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, dynamic-type -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: dynamic type triggers JVM-specific diagnostics in aliases, bounds, and declarations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
typealias Dyn1 = <!TYPEALIAS_SHOULD_EXPAND_TO_CLASS!>dynamic<!>


// TESTCASE NUMBER: 2
fun <T : <!DYNAMIC_UPPER_BOUND!>dynamic<!>> case_2(): T = TODO()


// TESTCASE NUMBER: 3
interface Inv3<T : <!DYNAMIC_UPPER_BOUND!>dynamic<!>>


// TESTCASE NUMBER: 4
abstract class Case4<T : <!DYNAMIC_UPPER_BOUND!>dynamic<!>>


// TESTCASE NUMBER: 5
typealias Dyn5 = <!TYPEALIAS_SHOULD_EXPAND_TO_CLASS!>dynamic<!>
