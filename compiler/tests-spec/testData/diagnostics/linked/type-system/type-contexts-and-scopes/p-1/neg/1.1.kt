// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-contexts-and-scopes -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Type visibility and scope rules reject inaccessible or unresolved types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer12 {
    private class Hidden
}

fun <!EXPOSED_FUNCTION_RETURN_TYPE!>case_1<!>(): Outer12.<!INVISIBLE_REFERENCE!>Hidden<!> = Outer12.<!INVISIBLE_MEMBER!>Hidden<!>()


// TESTCASE NUMBER: 2
fun case_2(x: <!UNRESOLVED_REFERENCE!>MissingType<!>) {}


// TESTCASE NUMBER: 3
class Outer14 {
    class Real
}

fun case_3() {
    val x: Outer14.<!UNRESOLVED_REFERENCE!>Fake<!> = Outer14.<!UNRESOLVED_REFERENCE!>Fake<!>()
}


// TESTCASE NUMBER: 4
class Outer15 {
    private object Hidden
}

fun <!EXPOSED_FUNCTION_RETURN_TYPE!>case_4<!>(): Outer15.<!INVISIBLE_REFERENCE!>Hidden<!> = Outer15.<!INVISIBLE_MEMBER!>Hidden<!>


// TESTCASE NUMBER: 5
class Outer16 {
    private class Hidden
}

fun <!EXPOSED_FUNCTION_RETURN_TYPE!>case_5<!>(): Outer16.<!INVISIBLE_REFERENCE!>Hidden<!> = Outer16.<!INVISIBLE_MEMBER!>Hidden<!>()
