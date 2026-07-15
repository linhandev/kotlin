// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Bounded type parameters reject multiple class upper bounds and invalid bound combinations
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class C1
open class C2

class A1<T> where T : C1, T : <!ONLY_ONE_CLASS_BOUND_ALLOWED!>C2<!>


// TESTCASE NUMBER: 2
open class C3
open class C4 : C3()

class A2<T> where T : C3, T : <!ONLY_ONE_CLASS_BOUND_ALLOWED!>C4<!>


// TESTCASE NUMBER: 3
enum class E3
open class C5

class A3<T> where T : C5, T : <!ONLY_ONE_CLASS_BOUND_ALLOWED!>E3<!>


// TESTCASE NUMBER: 4
object O4
open class C6

class A4<<!CONFLICTING_UPPER_BOUNDS!>T<!>> where T : <!FINAL_UPPER_BOUND!>O4<!>, T : <!ONLY_ONE_CLASS_BOUND_ALLOWED!>C6<!>


// TESTCASE NUMBER: 5
interface A5<K, V> where V : K, V : <!REPEATED_BOUND!>K<!>
