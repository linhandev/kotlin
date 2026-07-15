// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-contexts-and-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Local and qualified type names are not visible outside their defining scope
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer17 {
    fun make() {
        class Local
        val x: Local = Local()
    }
}

fun case_1(): <!UNRESOLVED_REFERENCE!>Local<!> = <!UNRESOLVED_REFERENCE!>Local<!>()


// TESTCASE NUMBER: 2
class Outer18 {
    class Nested
}

fun case_2(): <!UNRESOLVED_REFERENCE!>Nested<!> = <!UNRESOLVED_REFERENCE!>Nested<!>()


// TESTCASE NUMBER: 3
class Outer19<T> {
    inner class Inner(val t: T)
}

fun case_3(): <!UNRESOLVED_REFERENCE!>Inner<!> = Outer19<String>().Inner("")


// TESTCASE NUMBER: 4
class Outer20 {
    private class ProtectedNested
}

fun <!EXPOSED_FUNCTION_RETURN_TYPE!>case_4<!>(): Outer20.<!INVISIBLE_REFERENCE!>ProtectedNested<!> = Outer20.<!INVISIBLE_MEMBER!>ProtectedNested<!>()


// TESTCASE NUMBER: 5
fun case_5() {
    class Local21
}

fun case_5b(): <!UNRESOLVED_REFERENCE!>Local21<!> = <!UNRESOLVED_REFERENCE!>Local21<!>()
