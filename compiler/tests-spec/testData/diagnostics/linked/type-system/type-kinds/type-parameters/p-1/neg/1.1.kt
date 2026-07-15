// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Nested type declarations cannot capture parent type parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Parent1<T> {
    class Nested1(val t: <!UNRESOLVED_REFERENCE!>T<!>)
}


// TESTCASE NUMBER: 2
class Parent2<T> {
    object Obj2 {
        fun get(): <!UNRESOLVED_REFERENCE!>T<!> = TODO()
    }
}


// TESTCASE NUMBER: 3
class Parent3<T> {
    interface Nested3 {
        val value: <!UNRESOLVED_REFERENCE!>T<!>
    }
}


// TESTCASE NUMBER: 4
open class C4
open class C5

class Parent4<T> where T : C4, T : <!ONLY_ONE_CLASS_BOUND_ALLOWED!>C5<!>


// TESTCASE NUMBER: 5
enum class E5
open class C6

class Parent5<T> where T : C6, T : <!ONLY_ONE_CLASS_BOUND_ALLOWED!>E5<!>
